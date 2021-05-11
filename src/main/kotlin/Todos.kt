import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import react.setState

external interface TodosProps : RProps {}

data class TodosState(var todos: List<Pair<String, Boolean>>, var input: String) : RState

@ExperimentalJsExport
@JsExport
class Todos(props: TodosProps) : RComponent<TodosProps, TodosState>(props) {
    init {
        state = TodosState(listOf(Pair("take out trash", false), Pair("dishes", true)), "")
    }

    override fun RBuilder.render() {
        h1 {
            +"Todo app"
        }
        ul {
            state.todos.mapIndexed { index, todo ->
                li {
                    button {
                        attrs {
                            onClickFunction = {
                                setState {
                                    todos = todos.mapIndexed { i, t ->
                                        if (i == index) Pair(
                                            todo.first,
                                            !todo.second
                                        ) else t
                                    }
                                }
                            }
                        }
                        if (todo.second) {
                            +"+"
                        }
                        +todo.first
                    }
                }
            }
        }

        input {
            attrs {
                value = state.input
                onChangeFunction = {
                    setState {
                        input = (it.target as HTMLInputElement).value
                    }
                }
            }
        }
        button {
            attrs {
                onClickFunction = {
                    setState {
                        todos += Pair(input, false)
                        input = ""
                    }
                }
            }

            +"add"
        }
    }
}
