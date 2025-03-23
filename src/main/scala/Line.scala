case class Line(level: Int, id: Option[String], tag: String,
                xref: Option[String], value: Option[String]) {
  require(level >= 0 && level <= 99)
  require(id.isEmpty || id.get.length <= 20)
  require(tag.length <= 31)
}

private object Line {

  import java.util.regex.*

  private val LinePattern = Pattern.compile(
    "^\\s*(\\d)\\s+(@([^@ ]+)@\\s+)?(\\w+)(\\s+@([^@ ]+)@)?(\\s(.*))?$", Pattern.DOTALL)

  def parse(line: String): Option[Line] = {
    val m = LinePattern.matcher(line)
    if (!m.find)
      None
    else
      Some(Line(
        level = m.group(1).toInt,
        id = Option(m.group(3)),
        tag = m.group(4),
        xref = Option(m.group(6)),
        value = Option(m.group(8))))
  }
}
