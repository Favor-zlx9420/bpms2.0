package com.rong.console.center.controller.comm;

import com.rong.console.center.controller.BaseController;
import com.rong.console.center.util.RightConst;
import com.rong.console.center.util.ViewModel;
import com.rong.sys.module.entity.TbDictionary;
import com.rong.sys.module.query.TsDictionary;
import com.rong.sys.module.request.TqDictionary;
import com.rong.sys.module.view.TvDictionary;
import com.rong.sys.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RightConst.COMM_DICTIONARY_PATH)
public class DictionaryController extends BaseController<TbDictionary, TqDictionary, TvDictionary, DictionaryService> {

    @Autowired
    public DictionaryController() {
        super(new ViewModel.Builder()
                .basePath(RightConst.COMM_DICTIONARY_PATH)
                .build(), TsDictionary.Fields.id);
    }
}
