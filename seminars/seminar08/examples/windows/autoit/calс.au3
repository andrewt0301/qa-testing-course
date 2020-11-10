Run("calc.exe")
WinWaitActive("Calculator") ; ("Калькулятор")
AutoItSetOption("SendKeyDelay", 50)

Test()

MsgBox(0, "Test Finished", "Test is finished")

Exit

Func Test()
  Send("{ESC}{ESC}")
  Send("2")
  Send("{SHIFTDOWN}={SHIFTUP}") ;+
  Send("2")
  Send("{ENTER}")
  AssertEq(GetResult(), 4, "2 + 2 = 4")
EndFunc

Func GetResult()
  Send("^c")
  $str = StringReplace(ClipGet(), ",", ".")
  return Number($str)
EndFunc

Func Assert($Result, $Message)
  if False == $Result Then
    MsgBox(0, "Test Failed", $Message)
  EndIf
EndFunc

Func AssertEq($Result, $Expect, $Message)
  if $Expect <> $Result Then
    MsgBox(0, "Test Failed", "Expected: " & $Expect & " get: " & $Result & " " & $Message)
  EndIf
EndFunc

