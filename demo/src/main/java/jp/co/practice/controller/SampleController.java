package jp.co.practice.controller;

import jp.co.practice.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SampleController {
    private final SampleService sampleService;

    /**
     * データ取得 サンプル
     * @param model
     * @return
     */
    @GetMapping("/sample")
    public String showSample(Model model) {
        return "sample/index.html";
    }
}
