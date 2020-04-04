package quizapp.commons;

public interface Assembler<F, T> {
    T map(F from);
    F revers(T from);
}
