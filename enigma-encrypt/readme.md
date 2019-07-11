# Statement
## Goal
During World War II, the Germans were using an encryption code called Enigma – 
which was basically an encryption machine that encrypted messages for transmission.
The Enigma code went many years unbroken.

* Here How Basic Machine works:
First Caesar shift is applied using an incrementing number.
If AAA and starting number is 4 then output will be EFG.
A + 4 = E
A + 4 + 1 = F
A + 4 + 1 + 1 = G

Now EFG from first ROTOR such as "ABCDEFGHIJKLMNOPQRSTUVWXYZ" --> "BDFHJLCPRTXVZNYEIWGAKMUSQO"
so EFG become "JLC".

Then it is passed through 2 more rotors to get final value.

If the second ROTOR is "AJDKSIRUXBLHWTMCQGZNPYFVOE", we apply the substitution step again thus:
ABCDEFGHIJKLMNOPQRSTUVWXYZ
AJDKSIRUXBLHWTMCQGZNPYFVOE
So "JLC" becomes "BHD".

If the third ROTOR is "EKMFLGDQVZNTOWYHXUSPAIBRCJ", then the final substitution is "BHD" becoming "KQF".
Final Output is sent via Radio Transmitter. 