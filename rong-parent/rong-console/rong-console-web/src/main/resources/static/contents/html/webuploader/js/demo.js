var serviceType = 30;
var hadUpload = true;
var savePath = 'video';
var fileMd5 = "";
var fileUrl = "";

var toThousandth = function (num, mil) {//千分位处理(四舍五入)，num：字符串/数值，mil：小数位
    if (num == null || num.length == 0 || isNaN(num)) {
        num = 0;
    }
    if (mil > 20) {
        mil = 20;
    }
    return (parseFloat(num).toFixed(mil) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
}
jQuery(function () {
    var $ = jQuery,    // just in case. Make sure it's not an other libaray.

        $wrap = $('#uploader'),

        // 图片容器
        $queue = $('<ul class="filelist"></ul>')
            .appendTo($wrap.find('.queueList')),

        // 状态栏，包括进度和控制按钮
        $statusBar = $wrap.find('.statusBar'),

        // 文件总体选择信息。
        $info = $statusBar.find('.info'),

        // 上传按钮
        $upload = $wrap.find('.uploadBtn'),

        // 没选择文件之前的内容。
        $placeHolder = $wrap.find('.placeholder'),

        // 总体进度条
        $progress = $statusBar.find('.progress').hide(),

        // 添加的文件数量
        fileCount = 0,

        // 添加的文件总大小
        fileSize = 0,

        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 110 * ratio,
        thumbnailHeight = 110 * ratio,

        // 可能有pedding, ready, uploading, confirm, done.
        state = 'pedding',

        // 所有文件的进度信息，key为file id
        percentages = {},

        supportTransition = (function () {
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                    'WebkitTransition' in s ||
                    'MozTransition' in s ||
                    'msTransition' in s ||
                    'OTransition' in s;
            s = null;
            return r;
        })(),

        // WebUploader实例
        uploader;

    if (!WebUploader.Uploader.support()) {
        alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error('WebUploader does not support the browser you are using.');
    }
    //发送文件之前先将文件md5与服务器上的做对比，假如服务器上已经存在有该文件，则直接返回服务器地址（秒传）
    //如果服务器上没有，则返回已经上传的分片信息，此时客户端可以跳过已经上传过后的分片。继续上传剩下的分片。
    WebUploader.Uploader.register({
        'before-send': 'checkChunk',
        //'before-send-file': 'checkFile',
        'add-file': 'checkFile'
    }, {
        checkFile: function (files) {
            $upload.addClass('disabled');
            var file = files[0];
            uploader.md5File(file)
                .progress(function (percentage) {
                    $("#md5-process").html("准备进度：" + toThousandth(percentage * 100, 2) + "%");
                    //console.log('percentage:', percentage);
                })
                // 完成
                .then(function (fmd5) {
                    fileMd5 = fmd5;
                    //console.log('file md5 result:', fileMd5);
                    $.get("/comm/imagesams/checkFile",
                        {
                            savePath: savePath,
                            fileMd5: fileMd5,
                            serviceType: serviceType,
                            ext: file.ext
                        },
                        function (data) {
                            if (data.code != "200") {
                                console.info("文件上传出现异常！");
                                alert(data.message);
                            } else {
                                $upload.removeClass('disabled');
                                file.hadUpload = data.content.exists;
                                if (file.hadUpload) {
                                    console.info(data.content);//data.content.fileUrl;
                                }
                                fileUrl = data.content.fileUrl;
                                //设置当前已经上传的分片
                                file.hadChunks = data.content.hadChunks;
                            }
                        });
                });
        },
        checkChunk: function (block) {
            var blob = block.blob,
                deferred = $.Deferred();
            var oFile = block.file.source;
            var hasChunks = oFile.hadChunks;
            if (oFile.hadUpload || (hasChunks && hasChunks.indexOf(block.chunk + "") !== -1)) {
                deferred.reject();
            } else {
                //没有，继续上传
                deferred.resolve();
            }
            // uploader.md5File(block.blob)
            // // 及时显示进度
            //     .progress(function(percentage) {
            //         console.log('percentage:', percentage);
            //     })
            //     // 完成
            //     .then(function(val) {
            //         console.log('md5 result:', val);
            //     });

            return deferred.promise();
        }
    });

    // 实例化
    uploader = WebUploader.create({
        pick: {
            id: '#filePicker',
            label: '点击选择视频'
        },
        dnd: '#uploader .queueList',
        paste: document.body,

        accept: {
            title: 'Videos',
            extensions: 'swf,flv,avi,mpg,rm,rmvb,mp4',
            mimeTypes: 'video/*'
        },

        // swf文件路径
        swf: '/contents/scripts/uploader/Uploader.swf',

        disableGlobalDnd: true,

        chunked: true,
        // 分片大小，默认4M
        chunkSize: 1024 * 1024 * 1,//测试 4M
        // 分片出错后（如网络原因等造成出错）的重传次数
        chunkRetry: 3,
        // 上传并发数
        threads: 1,
        server: '/comm/imagesams/upload',
        fileNumLimit: 1,
        fileSizeLimit: 1024 * 1024 * 2300,    // 300 M
        fileSingleSizeLimit: 1024 * 1024 * 2300    // 300 M
    });
    uploader.on('uploadBeforeSend', function (block, data) {
        var deferred = $.Deferred();
        var oFile = block.file.source;
        data.fileMd5 = fileMd5;
        data.savePath = savePath;
        data.ext = oFile.ext;
        data.serviceType = serviceType;
        return deferred.promise();
    });

    // // 添加“添加文件”的按钮，
    // uploader.addButton({
    //     id: '#filePicker2',
    //     label: '继续添加'
    // });

    // 当有文件添加进来时执行，负责view的创建
    function addFile(file) {
        var $li = $('<li id="' + file.id + '">' +
            '<p class="title">' + file.name + '</p>' +
            '<p class="imgWrap"></p>' +
            '<p class="progress"><span></span></p>' +
            '</li>'),

            $btns = $('<div class="file-panel">' +
                '<span class="cancel">删除</span>' +
                '<span class="rotateRight">向右旋转</span>' +
                '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
            $prgress = $li.find('p.progress span'),
            $wrap = $li.find('p.imgWrap'),
            $info = $('<p class="error"></p>'),

            showError = function (code) {
                switch (code) {
                    case 'exceed_size':
                        text = '文件大小超出';
                        break;

                    case 'interrupt':
                        text = '上传暂停';
                        break;

                    default:
                        text = '上传失败，请重试';
                        break;
                }

                $info.text(text).appendTo($li);
            };

        if (file.getStatus() === 'invalid') {
            showError(file.statusText);
        } else {
            // @todo lazyload
            $wrap.text('预览中');
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $wrap.text('不能预览');
                    return;
                }

                var img = $('<img src="' + src + '">');
                $wrap.empty().append(img);
            }, thumbnailWidth, thumbnailHeight);

            percentages[file.id] = [file.size, 0];
            file.rotation = 0;
        }

        file.on('statuschange', function (cur, prev) {
            if (prev === 'progress') {
                $prgress.hide().width(0);
            } else if (prev === 'queued') {
                $li.off('mouseenter mouseleave');
                $btns.remove();
            }

            // 成功
            if (cur === 'error' || cur === 'invalid') {
                console.log(file.statusText);
                showError(file.statusText);
                percentages[file.id][1] = 1;
            } else if (cur === 'interrupt') {
                showError('interrupt');
            } else if (cur === 'queued') {
                percentages[file.id][1] = 0;
            } else if (cur === 'progress') {
                $info.remove();
                $prgress.css('display', 'block');
            } else if (cur === 'complete') {
                $li.append('<span class="success"></span>');
                parent.addVideo(fileUrl);
                parent.lBox.closeWin();
            }

            $li.removeClass('state-' + prev).addClass('state-' + cur);
        });

        $li.on('mouseenter', function () {
            $btns.stop().animate({height: 30});
        });

        $li.on('mouseleave', function () {
            $btns.stop().animate({height: 0});
        });

        $btns.on('click', 'span', function () {
            var index = $(this).index(),
                deg;

            switch (index) {
                case 0:
                    uploader.removeFile(file);
                    return;

                case 1:
                    file.rotation += 90;
                    break;

                case 2:
                    file.rotation -= 90;
                    break;
            }

            if (supportTransition) {
                deg = 'rotate(' + file.rotation + 'deg)';
                $wrap.css({
                    '-webkit-transform': deg,
                    '-mos-transform': deg,
                    '-o-transform': deg,
                    'transform': deg
                });
            } else {
                $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                // use jquery animate to rotation
                // $({
                //     rotation: rotation
                // }).animate({
                //     rotation: file.rotation
                // }, {
                //     easing: 'linear',
                //     step: function( now ) {
                //         now = now * Math.PI / 180;

                //         var cos = Math.cos( now ),
                //             sin = Math.sin( now );

                //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                //     }
                // });
            }


        });

        $li.appendTo($queue);
    }

    // 负责view的销毁
    function removeFile(file) {
        var $li = $('#' + file.id);

        delete percentages[file.id];
        updateTotalProgress();
        $li.off().find('.file-panel').off().end().remove();
    }

    function updateTotalProgress() {
        var loaded = 0,
            total = 0,
            spans = $progress.children(),
            percent;

        $.each(percentages, function (k, v) {
            total += v[0];
            loaded += v[0] * v[1];
        });

        percent = total ? loaded / total : 0;

        spans.eq(0).text(Math.round(percent * 100) + '%');
        spans.eq(1).css('width', Math.round(percent * 100) + '%');
        updateStatus();
    }

    function updateStatus() {
        var text = '', stats;

        if (state === 'ready') {
            text = '选中' + fileCount + '个视频，共' +
                WebUploader.formatSize(fileSize) + '。';
        } else if (state === 'confirm') {
            stats = uploader.getStats();
            if (stats.uploadFailNum) {
                text = '已成功上传' + stats.successNum + '张照片至XX相册，' +
                    stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
            }

        } else {
            stats = uploader.getStats();
            text = '共' + fileCount + '个（' +
                WebUploader.formatSize(fileSize) +
                '），已上传' + stats.successNum + '个';

            if (stats.uploadFailNum) {
                text += '，失败' + stats.uploadFailNum + '个';
            }
        }

        $info.html(text + "<span id='md5-process'></span>");
    }

    function setState(val) {
        var file, stats;

        if (val === state) {
            return;
        }

        $upload.removeClass('state-' + state);
        $upload.addClass('state-' + val);
        state = val;

        switch (state) {
            case 'pedding':
                $placeHolder.removeClass('element-invisible');
                $queue.parent().removeClass('filled');
                $queue.hide();
                $statusBar.addClass('element-invisible');
                uploader.refresh();
                break;

            case 'ready':
                $placeHolder.addClass('element-invisible');
                $('#filePicker2').removeClass('element-invisible');
                $queue.parent().addClass('filled');
                $queue.show();
                $statusBar.removeClass('element-invisible');
                uploader.refresh();
                break;

            case 'uploading':
                $('#filePicker2').addClass('element-invisible');
                $progress.show();
                $upload.text('暂停上传');
                break;

            case 'paused':
                $progress.show();
                $upload.text('继续上传');
                break;

            case 'confirm':
                $progress.hide();
                $upload.text('开始上传').addClass('disabled');

                stats = uploader.getStats();
                if (stats.successNum && !stats.uploadFailNum) {
                    setState('finish');
                    return;
                }
                break;
            case 'finish':
                stats = uploader.getStats();
                if (stats.successNum) {
                    //alert( '上传成功' );
                } else {
                    // 没有成功的图片，重设
                    state = 'done';
                    location.reload();
                }
                break;
        }

        updateStatus();
    }

    uploader.onUploadProgress = function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress span');

        $percent.css('width', percentage * 100 + '%');
        percentages[file.id][1] = percentage;
        updateTotalProgress();
    };

    uploader.onFileQueued = function (file) {
        fileCount++;
        fileSize += file.size;

        if (fileCount === 1) {
            $placeHolder.addClass('element-invisible');
            $statusBar.show();
        }

        addFile(file);
        setState('ready');
        updateTotalProgress();
    };

    uploader.onFileDequeued = function (file) {
        fileCount--;
        fileSize -= file.size;

        if (!fileCount) {
            setState('pedding');
        }

        removeFile(file);
        updateTotalProgress();

    };


    uploader.on('all', function (type) {
        var stats;
        switch (type) {
            case 'uploadFinished':
                setState('confirm');
                break;

            case 'startUpload':
                setState('uploading');
                break;

            case 'stopUpload':
                setState('paused');
                break;

        }
    });

    uploader.onError = function (code, arg, file) {
        if (code === "Q_EXCEED_SIZE_LIMIT") {
            alert("文件大小限制：" + WebUploader.Base.formatSize(arg));
        } else {
            alert(code);
        }
    };

    $upload.on('click', function () {
        if ($(this).hasClass('disabled')) {
            return false;
        }

        if (state === 'ready') {
            uploader.upload();
        } else if (state === 'paused') {
            uploader.upload();
        } else if (state === 'uploading') {
            uploader.stop();
        }
    });

    $info.on('click', '.retry', function () {
        uploader.retry();
    });

    $info.on('click', '.ignore', function () {
        alert('todo');
    });

    $upload.addClass('state-' + state);
    updateTotalProgress();
});