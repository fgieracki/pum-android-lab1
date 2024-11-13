# LAB PUM - Android
### Zadania:

#### Zadanie 1
Twoim zadaniem będzie dodanie nowego Build Variantu i wyświetlenie innej treści w zależności od wybranego Variantu. Pamiętaj, żeby zsynchronizować twój build.gradle.kts po każdej zmianie.


1. Dodaj nowy buildType w buld.gradle.kts aplikacji.
```kotlin 
buildTypes{
    ...
        create("someOtherBuildType") {
            isDebuggable = true
        }
```
2. Dodaj nowy productFlavor
```kotlin
compileSdk = 35
    ...
    flavorDimensions += "example"
    productFlavors {
        create("demo") {
            dimension = "example"
        }
    }
    ...
```
3. Dodaj dependencję dla nowego productFlavoru 
```kotlin
dependencies {
    ...
    "demoImplementation"(libs.androidx.ui.tooling)
    "demoImplementation"(libs.androidx.ui.test.manifest)
}
```
4. Wybierz odpowiedni build variant. (np. demoSomeOtherBuildType) Build -> Select Build Variant
5. Utwórz nowy sourceSet dla twojego build Variantu. Zaznacz folder app -> new directory -> wybierz odpowiedni sourceSet
6. Stwórz funkcję która wyświetla tekst zawierający nazwę sourceSetu.
```kotlin
@Composable
fun ExampleText() {
    Text("Example Text DemoSomeOtherBuildVariant")
}
```
7. Powtórz kroki 4-6 dla variantu Debug
8. Dodaj ExampleText do Preview
```kotlin
        Column(modifier = Modifier.padding(16.dp)) {
            Greeting("Android")
            ExampleTextMain()
            ExampleText()
        }
```
9. Poeksperymentuj wybierając różne buildy w preview. Odpowiedz na pytania:
- Jaki tekst jest wyświetlany?
- Po co istnieje tak funkcjonalność?
- Co dzieje się z sourceSetami w widoku projektu?
- Co dzieje się z funkcją ExampleTextMain?

#### Zadanie 2
Uzupełnij kod oznaczony TODO

#### Zadanie 3
Na podstawie plików aplikacji zobacz jakie warunki muszą zachodzić aby wytriggerować toasta z napisem "EASTEREGG" oraz używając narzędzi Virtual Device wywołaj to wydarzenie
