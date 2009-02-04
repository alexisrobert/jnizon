D[f_ + g_, s_] := D[f, s] + D[g, s]
;
D[f_*g_, s_] := f*D[g, s] + g*D[f, s]
;
D[n_*s_, s_] := n
;
Print[42]
