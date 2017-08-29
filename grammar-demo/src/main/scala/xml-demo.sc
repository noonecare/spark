import scala.xml.Elem
// 打印出 text
<a href="http://www.zhibo8.com">直播吧</a>.text
// 使用正则表达式解析出指定的内容
<a href="http://www.zhibo8.com">直播吧</a> \ "@href"

// serialize
abstract class CCTherm {
    val description: String
    val yearMade: Int
    val dateObtained: String
    val bookPrice: Int
    val purchasePrice: Int
    val condition: Int
    
    override def toString: String = description
    
    def toXML: Elem =
        <cctherm>
            <description>
                {description}
            </description>
            <yearMade>
                {yearMade}
            </yearMade>
            <dateObtained>
                {dateObtained}
            </dateObtained>
            <bookPrice>
                {bookPrice}
            </bookPrice>
            <purchasePrice>
                {purchasePrice}
            </purchasePrice>
            <condition>
                {condition}
            </condition>
        </cctherm>
}

val therm = new CCTherm {
    override val description: String = "hot dog"
    override val yearMade: Int = 1952
    override val dateObtained: String = "March 14, 2016"
    override val bookPrice: Int = 2199
    override val purchasePrice = 500
    override val condition: Int = 9
}

therm.toXML


// deserialize
def fromXML(node: scala.xml.Node): CCTherm =
    new CCTherm {
        val description: String = (node \ "description").text
        val yearMade: Int = (node \ "yearMade").text.toInt
        val dateObtained: String = (node \ "dateObtained").text
        val bookPrice: Int = (node \ "bookPrice").text.toInt
        val purchasePrice: Int = (node \ "purchasePrice").text.toInt
        val condition: Int = (node \ "condition").text.toInt
    }

// save
val node = <a href="http://www.zhibo8.com">直播吧</a>
scala.xml.XML.save("therm1.xml", node, enc = "utf-8")

// load
val loadNode = xml.XML.loadFile("therm1.xml")

def proc(node: scala.xml.Node): String =
    node match {
        case <a>
            {contents}
            </a> => "It's an a: " + contents
        case <b>
            {contents}
            </b> => "It's a b: " + contents
        case _ => "It's something else."
    }

proc(<a>apple</a>)
proc(<b>banana</b>)

// string to xml
val content = "<p>when I grown up, I get confused.</p>"
xml.XML.loadString(content).text

// xml to string
xml.XML.loadString(content).toString
