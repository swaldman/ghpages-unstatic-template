package ghpages.unstatic.template

case class Params(
  siteServerAbsUrl      : String,
  title                 : String,
  maxFrontPageEntries   : Int    = 5,
  timeZone              : String = "America/New_York",
  sitePath              : String = "/",
  sitePackage           : String = "my.unstatic.website",
  millVersion           : String = "1.0.6",
  millJvmVersion        : String = "21",
  untemplateMillVersion : String = "0.3.0",
  unstaticVersion       : String = "0.4.0",
  scalaVersion          : String = "3.3.7",
  entriesSubpackage     : String = "entries",
  entriesDir            : String = "entries",
):
  val entriesPackage =
    if entriesSubpackage.length > 0 then s"${sitePackage}.${entriesSubpackage}" else s"${sitePackage}"

  val basePathExpression =
    if sitePath.isBlank() || sitePath == "/" then "Rooted.root" else s"""Rooted( "${sitePath}" )"""
