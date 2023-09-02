package jp.co.practice.controller.ui.toppage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller
 */
@Controller
@RequiredArgsConstructor
public class SampleController {

    /**
     * トップページを表示します。
     * @return トップページ
     */
    @GetMapping("/")
    public String index(Model model) {
        return "sample/index.html";
    }
}