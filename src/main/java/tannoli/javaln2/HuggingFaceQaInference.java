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
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HuggingFaceQaInference {

    @PostMapping(value = "/api/answer", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String input(@RequestBody Map<String, String> payload)
            throws IOException, TranslateException, ModelException {
        String paragraph = payload.get("paragraph");
        String question = payload.get("question");
        QAInput input = new QAInput(question, paragraph);
        String answer = HuggingFaceQaInference.qapredict(input);
        return "The answer is:\n" + answer;
    }

    public static String qapredict(QAInput input) throws IOException, TranslateException, ModelException {
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