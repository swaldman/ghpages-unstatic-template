package buildutil

val metabuildRoot = os.Path( sys.env("MILL_WORKSPACE_ROOT") ) / "mill-build"

def resolveLayoutMainHtmlUntemplate( replacements : Map[String,String] ) : String =
  val tt = trivialtemplate.TrivialTemplate(os.read(os.Path("trivialtemplate/layout-main.html.untemplate", metabuildRoot)))
  tt.resolve( replacements )

def staticLayoutEntryHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/layout-entry.html.untemplate", metabuildRoot) )

def staticLayoutArchiveHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/layout-archive.html.untemplate", metabuildRoot) )

def staticLayoutUpdateHistoryHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/layout-update-history.html.untemplate", metabuildRoot) )

def staticEntrySeparatorHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/entry-separator.html.untemplate", metabuildRoot) )


