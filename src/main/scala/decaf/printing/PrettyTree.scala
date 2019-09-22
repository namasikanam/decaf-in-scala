package decaf.printing

import decaf.annot.Annotated
import decaf.lowlevel.log.IndentPrinter
import decaf.tree.TreeNode.Node

class PrettyTree(printer: IndentPrinter) extends PrettyPrinter[Node with Annotated[_]](printer) {

  var showPos = true

  var showAnnot = false

  def prettyElement(element: Any): Unit = element match {
    case null => printer.println("<null>")
    case e: Node with Annotated[_] => pretty(e)
    case Some(e) => prettyElement(e)
    case None => printer.println("<none>")
    case es: List[_] =>
      printer.println("List")
      withIndent {
        if (es.isEmpty) printer.println("<empty>")
        else es.foreach(prettyElement)
      }
    case e => printer.println(e.toString)
  }

  override def pretty(node: Node with Annotated[_]): Unit = {
    val annotStr = if (showAnnot) s" { ${ node.annot } }" else ""
    val posStr = if (showPos) s" @ (${ node.pos.line },${ node.pos.column })"

    printer.println(node.productPrefix + annotStr + posStr)
    withIndent {
      node.productIterator.foreach(prettyElement)
    }
  }
}
