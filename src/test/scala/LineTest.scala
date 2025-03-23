class LineTest extends org.scalatest.funspec.AnyFunSpec {
  describe("line parser") {
    it ("should parse an INDI line") {
      val result = Line.parse("0 @I7@ INDI").get
      assert(result.level == 0)
      assert(result.id.get == "I7")
      assert(result.tag == "INDI")
      assert(result.value.isEmpty)
      assert(result.xref.isEmpty)
    }
    it ("should parse a NAME line") {
      val result = Line.parse("1 NAME Ginette /GEFRETTE/").get
      assert(result.level == 1)
      assert(result.id.isEmpty)
      assert(result.tag == "NAME")
      assert(result.value.get == "Ginette /GEFRETTE/")
      assert(result.xref.isEmpty)
    }
    it ("should parse a SEX line") {
      val result = Line.parse("1 SEX F").get
      assert(result.level == 1)
      assert(result.id.isEmpty)
      assert(result.tag == "SEX")
      assert(result.value.get == "F")
      assert(result.xref.isEmpty)
    }
    it ("should parse an OCCU line") {
      val result = Line.parse("1 OCCU Ménagère").get
      assert(result.level == 1)
      assert(result.id.isEmpty)
      assert(result.tag == "OCCU")
      assert(result.value.get == "Ménagère")
      assert(result.xref.isEmpty)
    }
    it ("should parse a DATE line") {
      val result = Line.parse("2 DATE 03 DEC 1996").get
      assert(result.level == 2)
      assert(result.id.isEmpty)
      assert(result.tag == "DATE")
      assert(result.value.get == "03 DEC 1996")
      assert(result.xref.isEmpty)
    }
    it ("should parse a SOUR line") {
      val result = Line.parse("2 SOUR https://www.website.org/source/123456").get
      assert(result.level == 2)
      assert(result.id.isEmpty)
      assert(result.tag == "SOUR")
      assert(result.value.get == "https://www.website.org/source/123456")
      assert(result.xref.isEmpty)
    }
    it ("should parse a PLAC line") {
      val result = Line.parse("2 PLAC Mufflin-sur-Poilade, 12345, Groland-du-Bas, Groland").get
      assert(result.level == 2)
      assert(result.id.isEmpty)
      assert(result.tag == "PLAC")
      assert(result.value.get == "Mufflin-sur-Poilade, 12345, Groland-du-Bas, Groland")
      assert(result.xref.isEmpty)
    }
    it ("should parse a FAMC/FAMS line") {
      val result = Line.parse("1 FAMC @F8@").get
      assert(result.level == 1)
      assert(result.id.isEmpty)
      assert(result.tag == "FAMC")
      assert(result.value.isEmpty)
      assert(result.xref.get == "F8")
    }
    it ("should not parse an empty line") {
      assert(Line.parse("").isEmpty)
    }
  }
}
