# Jetbrains IntelliJ Idea BUG
This example could be used to reproduce problems with test scope transient dependencies.

Following the list of possible BUGs related:
 - [SCL-11083](https://youtrack.jetbrains.com/issue/SCL-11083)
 - [SCL-8396](https://youtrack.jetbrains.com/issue/SCL-8396)
 - [SCL-10912](https://youtrack.jetbrains.com/issue/SCL-10912)
 - [Stackoverflow thread](http://stackoverflow.com/questions/41564915/why-could-not-find-implicit-error-in-scala-intellij-scalatest-scalactic)

## How to reproduce error:
0. Check out the project.
2. All is working from sbt:
  ~~~
$ sbt clean test
[info] Loading global plugins from /home/angelcervera/.sbt/0.13/plugins
[info] Loading project definition from /home/angelcervera/projects/idea-dependencies/project
[info] Set current project to idea-dependencies-bug (in build file:/home/angelcervera/projects/idea-dependencies/)
[success] Total time: 0 s, completed 04-Feb-2017 17:38:05
[info] Updating {file:/home/angelcervera/projects/idea-dependencies/}idea-dependencies...
[info] Resolving jline#jline;2.12.1 ...
[info] Done updating.
[info] Compiling 1 Scala source to /home/angelcervera/projects/idea-dependencies/target/scala-2.11/test-classes...
[info] SimpleTest:
[info] - Simple Test
[info]   + Given Why this error? 
[info] Run completed in 178 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 10 s, completed 04-Feb-2017 17:38:15
  ~~~
  
2. Open Idea IDE and select "Import Project" -> "Import project from external model / SBT" -> "Use auto-import" / "Project SDK : 1.8"
3. Contextual menu of "SimpleTest" -> "Run 'SimpleTest'"
~~~
Error:(5, 10) could not find implicit value for parameter pos: org.scalactic.source.Position
    Given("Why this error?")
Error:(5, 10) not enough arguments for method Given: (implicit pos: org.scalactic.source.Position)Unit.
Unspecified value parameter pos.
    Given("Why this error?")
Error:(6, 11) could not find implicit value for parameter prettifier: org.scalactic.Prettifier
    assert("ok" === "ok")
Error:(6, 11) not enough arguments for macro method assert: (implicit prettifier: org.scalactic.Prettifier, implicit pos: org.scalactic.source.Position)org.scalatest.Assertion.
Unspecified value parameters prettifier, pos.
    assert("ok" === "ok")
Error:(4, 23) could not find implicit value for parameter pos: org.scalactic.source.Position
  test("Simple Test") {
~~~
3. And in other weird situations, Use "Build -> Rebuild Project"
~~~
Error:(6, 11) exception during macro expansion: 
java.lang.NoSuchMethodError: org.scalactic.BooleanMacro.genMacro(Lscala/reflect/api/Exprs$Expr;Ljava/lang/String;Lscala/reflect/api/Exprs$Expr;)Lscala/reflect/api/Exprs$Expr;
	at org.scalatest.AssertionsMacro$.assert(AssertionsMacro.scala:34)
    assert("ok" === "ok")
~~~


## Workaround:
Of course, It possible remove problematics libraries wrongly imported by Idea from `File -> Project Structure -> Modules -> Tab dependecies`.
In this case:
1. org.scalatest:scalatest_2.11:2.0.6:jar
2. org.scalactic:scalactic_2.11:2.0.6:jar

## Tested in:
~~~
IntelliJ IDEA 2017.1 EAP
Build #IU-171.2822.15, built on February 1, 2017
IntelliJ IDEA EAP User
Expiration date: March 3, 2017
JRE: 1.8.0_112-release-b702 amd64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
~~~

