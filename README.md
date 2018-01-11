# xspeedit_answer
Answer to xspeedit use case.

* L'algorithme se trouve dans `src/main/java/com/lbr/ArticleService.java` 

* Les sources sont compilables via Maven.

* L'application utilise SpringBoot, elle est lançable en tant qu'application Java (cf. `com/lbr/XspeeditApplication.java`)

* L'application est utilisable via un GET et son URL associée est : `/articles/optimize/{articles}`

* Exemple : `http://localhost:8080/articles/optimize/465464559879132324654897231676545132164654313`

donnera le retour JSON suivant :

```JSON
{
	"totalSize":204,
	"numberOfBoxesPlanned":21,
	"packing":"91/91/91/82/82/73/73/73/64/64/64/64/64/64/64/55/55/55/541/3331/22",
	"error":false
}
```

* Compatibilité Java 8

* Lancer l'application :

via SpringBoot et Maven :
`mvn spring-boot:run`

via le JAR compilé :
`java -jar xspeedit.jar`
