D[f_ + g_, s_] := D[f, s] + D[g, s]
;
D[f_*g_, s_] := f*D[g, s] + g*D[f, s]
;
D[n_*s_, s_] := Condition[n, FreeQ[n, s]]
;
D[k_, s_] := Condition[0, FreeQ[k, s]]
;
D[s_, s_] := 1
;
Print[42]
