import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalJsExport::class)
fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(Todos::class) {}
        }
    }
}
