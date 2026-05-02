package buildutil

val metabuildRoot = os.Path( sys.env("MILL_WORKSPACE_ROOT") ) / "mill-build"

def resolveLayoutMainHtmlUntemplate( replacements : Map[String,String] ) : String =
  val tt = trivialtemplate.TrivialTemplate(os.read(os.Path("trivialtemplate/layout-main.html.untemplate", metabuildRoot)))
  tt.resolve( replacements )

def resolveLayoutHelloPost( replacements : Map[String,String] ) : String =
  val tt = trivialtemplate.TrivialTemplate(os.read(os.Path("trivialtemplate/entry-hello.md.untemplate", metabuildRoot)))
  tt.resolve( replacements )

def staticLayoutEntryHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/layout-entry.html.untemplate", metabuildRoot) )

def staticLayoutArchiveHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/layout-archive.html.untemplate", metabuildRoot) )

def staticLayoutUpdateHistoryHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/layout-update-history.html.untemplate", metabuildRoot) )

def staticEntrySeparatorHtmlUntemplate : String =
  os.read( os.Path("static/layout-untemplates/entry-separator.html.untemplate", metabuildRoot) )

def staticCssStyle : String =
  os.read( os.Path("static/css/style.css", metabuildRoot) )

def copyImageInto( dir : os.Path ) =
  os.copy.into( os.Path("static/image", metabuildRoot), dir )

def copyDotGithubAs( dir : os.Path ) =
  os.copy( os.Path("static/_github", metabuildRoot), dir )

def copySiteScriptAs( file : os.Path ) =
  os.copy( os.Path("static/script/site", metabuildRoot), file )

def copyRepublishScriptAs( file : os.Path ) =
  os.copy( os.Path("static/script/republish.sh", metabuildRoot), file )

def copyDevcycleScriptAs( file : os.Path ) =
  os.copy( os.Path("static/script/devcycle", metabuildRoot), file )

def copyGitignoreAs( file : os.Path ) =
  os.copy( os.Path("static/_gitignore", metabuildRoot), file )


