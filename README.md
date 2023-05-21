## Model Deployment & Maintenance

Das vorliegende Projekt ist die Arbeit von Oliver Tanno. Es wurde im Rahmen des Moduls "Model Deployment & Maintenance" an der ZHAW School of Management and Law erstellt.

## Model Source
 - Model Name: bert-base-german-cased
 - Link: https://gist.github.com/KexinFeng/97e6344556f88822650d023acfbdf4f5

## Beschreibung
Es handelt sich um eine NLP Model, welches auf dem BERT Model von Google basiert. Es wurde mit dem Huggingface Framework trainiert und ist in der Lage, deutsche Texte zu klassifizieren. Das Model wurde mit dem Datensatz "German Hate Speech" trainiert, welcher auf Kaggle zur Verfügung steht. Das Model ist in der Lage, Texte in die Kategorien "Hate Speech", "Offensive Language" und "Neither" zu klassifizieren.

Im Textfeld "Paragraph" wird der zu analysierende Text eingetragen. Das Textfeld "Question" beinhaltet die Frage, welche das Model beantworten soll. Die Frage kann frei gewählt werden. Das Model wird mit dem Button "Answer Question" ausgeführt. Das Resultat wird als Answer ausgegeben.