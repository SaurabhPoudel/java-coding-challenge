package com.unbabel.challenge.controller;


import com.unbabel.challenge.model.TranslationRequest;
import com.unbabel.challenge.model.TranslationResponse;
import com.unbabel.challenge.repositories.MessageRepository;
import com.unbabel.challenge.repositories.TranslationResponseRepository;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class IndexController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TranslationResponseRepository tr;

    @Value("${java.challenge.company}")
    private String company; // Reads this value from Spring properties file


    private String translated = " ";
    // private String lan=" ";
    private String msg = " ";
    private String source = "";
    private String target = "";
    TranslationResponse translationResponse = new TranslationResponse();

    /**
     * Accepts get requests to the "/" url, generates random messages
     * and renders them in thymeleaf template (index.html).
     *
     * @param model inject objects into thymeleaf template
     * @return generated html page using thymeleaf
     */
    @GetMapping("/")
    public String main(Model model) {
        //set variables to be used in thymeleaf template
        model.addAttribute("translated", translated);
        model.addAttribute("msg", msg);
        model.addAttribute("source", source);
        model.addAttribute("company", company);
        model.addAttribute("messages", tr.findAll());
        return "index"; //thymeleaf template name (index -> index.html)
    }

    @GetMapping("/translatePage")
    public String translatePage(Model model) {
       // languages();
        model.addAttribute("msg", msg);
        model.addAttribute("source", source);
        model.addAttribute("target", target);
        model.addAttribute("FromLanguage", translationResponse.getSource_language());
        model.addAttribute("OriginalMsg", translationResponse.getText());
        model.addAttribute("TargetMsg", translationResponse.getTarget_language());
        model.addAttribute("company", company);
        model.addAttribute("messages", tr.findAll());

        return "translatePage";
    }

    @PostMapping("/translate")
    public String toTranslate(String msg, String source, String target, Model model) {

        TranslationResponse trans = translate(msg, source, target);

        model.addAttribute("msg", msg);
        model.addAttribute("source", source);
        model.addAttribute("FromLanguage", trans.getSource_language());
        model.addAttribute("target", target);
        model.addAttribute("OriginalMsg", trans.getText());
        model.addAttribute("TargetMsg", trans.getTarget_language());
        model.addAttribute("company", company);
        model.addAttribute("messages", tr.findAll());
        return "translatePage";
    }
    /*private String languages(){
        String API_KEY = "fullstack-challenge:9db71b322d43a6ac0f681784ebdcc6409bb83359";
        Unirest.config().setObjectMapper(new JacksonObjectMapper());

            String result="";
            result= Unirest.get("https://sandbox.unbabel.com/tapi/v2/language_pair/")
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiKey " + API_KEY)
                .getBody()
                .asObject()
                .getJSONObject("object")
                .getJSONArray("objects")
                .get(0);

        System.out.println(result);
        return result;
    }*/

    private TranslationResponse translate(String msg, String source, String target) {
        String API_KEY = "fullstack-challenge:9db71b322d43a6ac0f681784ebdcc6409bb83359";
        Unirest.config().setObjectMapper(new JacksonObjectMapper());

        TranslationResponse response = Unirest.post("https://sandbox.unbabel.com/tapi/v2/translation/")
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiKey " + API_KEY)
                .body(new TranslationRequest(msg, source, target, "text"))
                .asObject(TranslationResponse.class)
                .getBody();

        TranslationResponse translationResponse = Unirest.get("https://sandbox.unbabel.com/tapi/v2/translation/" + response.getUid() + "/")
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiKey " + API_KEY)
                .asObject(TranslationResponse.class)
                .getBody();
//cause of slow
        while (!translationResponse.getStatus().equals("completed")) {
            translationResponse = Unirest.get("https://sandbox.unbabel.com/tapi/v2/translation/" + response.getUid() + "/")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "ApiKey " + API_KEY)
                    .asObject(TranslationResponse.class)
                    .getBody();
        }
        tr.save(translationResponse);
        return translationResponse;
    }
}

