' Keystroke simulation with WScript Shell. For more information, see here:
'   https://social.technet.microsoft.com/wiki/contents/articles/5169.vbscript-sendkeys-method.aspx

Set WshShell = WScript.CreateObject("WScript.Shell")

WshShell.Run "calc.exe"
WScript.Sleep 1000

WshShell.AppActivate "Calculator"
WScript.Sleep 1000

WshShell.SendKeys "2"
WScript.Sleep 1000

WshShell.SendKeys "+=" ' +
WScript.Sleep 1000

WshShell.SendKeys "2"
WScript.Sleep 1000

WshShell.SendKeys "{ENTER}"
WScript.Sleep 1000

result = GetResult(WshShell)
AssertEq result, 4, "2 + 2 = 4"

WScript.Sleep 1000
WshShell.SendKeys "%{F4}"

MsgBox "Test is finished", 0, "Test Finished"

Sub AssertEq(Result, Expect, Message)
  if Expect <> Result Then
    MsgBox "Test: " & Message & ", Expected: " & Expect & ", Get: " & Result, 0, "Test Failed"
  End If
End Sub

Function GetResult(WshShell)
  WshShell.SendKeys "^c"
  WScript.Sleep 1000
  Str = GetClipboard()
  GetResult = CInt(Str)
End Function

Function GetClipboard()
  GetClipboard = CreateObject("HTMLFile").parentWindow.clipboardData.getData("Text")
End Function
