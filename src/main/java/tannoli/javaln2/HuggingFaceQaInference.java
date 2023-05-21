package tannoli.javaln2;

import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.nlp.qa.QAInput;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HuggingFaceQaInference {

    @PostMapping(value = "/api/answer", produces = "text/plain")
    public String input(@RequestParam("paragraph") String paragraph, @RequestParam("question") String question)
            throws IOException, TranslateException, ModelException {
        System.out.println("Test");
        QAInput input = new QAInput(question, paragraph);
        String answer = HuggingFaceQaInference.qa_predict(input);
        return ("The answer is: \n" + answer);
    }

    public static String qa_predict(QAInput input) throws IOException, TranslateException, ModelException {
        BertTranslator translator = new BertTranslator();
        Criteria<QAInput, String> criteria = Criteria.builder()
                .setTypes(QAInput.class, String.class)
                .optModelPath(Paths.get("src/main/resources/trace_cased_bertqa.pt"))
                .optTranslator(translator)
                .optProgress(new ProgressBar()).build();

        ZooModel<QAInput, String> model = criteria.loadModel();
        try (Predictor<QAInput, String> predictor = model.newPredictor(translator)) {
            return predictor.predict(input);
        }
    }
}