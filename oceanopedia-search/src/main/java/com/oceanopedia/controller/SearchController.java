package com.oceanopedia.controller;

import com.oceanopedia.service.SearchService;
import com.oceanopedia.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;


    @GetMapping("/searchByKeyWord")
    public BaseResult searchByKeyWord(String content) {
        return searchService.searchByKeyWord(content);
    }

    @GetMapping("/ESReload")
    public BaseResult esReload() {
        searchService.esinit();
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Done");
        return ok;
    }

    @Scheduled(cron = "0 0 */5 * * ?")
    public void scheduledESReload() {
        searchService.esinit();
    }

}
