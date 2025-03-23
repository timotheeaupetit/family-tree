@main
def main(): Unit = {
  val path = os.Path("/Users/timotheeaupetit/Projects/Perso/family-tree/data/taupetit_2025-03-18.ged")
  val lineStream = os.read.lines.stream(path)

  val line = Line.parse(lineStream.head).get

  println(line)

}