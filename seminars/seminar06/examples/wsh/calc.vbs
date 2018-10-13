'VBScript Example
Set WshShell = WScript.CreateObject("WScript.Shell")

WshShell.Run "calc.exe"
WshShell.AppActivate "Calculator"

WshShell.SendKeys "2"
'WshShell.SendKeys "{SHIFTDOWN}={SHIFTUP}"
WshShell.SendKeys "2"
WshShell.SendKeys "{ENTER}"
AssertEq GetResult(WshShell), 4, "2 + 2 = 4"

MsgBox "Test Finished", "Test is finished"

Sub AssertEq(Result, Expect, Message)
  if Expect <> Result Then
    MsgBox "Test Failed", "Expected: " & Expect & " get: " & Result & " " & Message
  End If
End Sub


Function GetResult(WshShell)
  WshShell.SendKeys "^c"
  Str = GetClipboard()
  GetResult = CInt(Str)
End Function

Function GetClipboard()
  GetClipboard = CreateObject("htmlfile").ParentWindow.ClipboardData.GetData("text")
End Function

