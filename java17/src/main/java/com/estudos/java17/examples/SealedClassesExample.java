package com.estudos.java17.examples;

/** sealed + records. Teoria: README.md. */
public final class SealedClassesExample {

    private SealedClassesExample() {
    }

    sealed interface Expr permits Lit, Add {
    }

    record Lit(int v) implements Expr {
    }

    record Add(Expr a, Expr b) implements Expr {
    }

    static int eval(Expr e) {
        if (e instanceof Lit l) {
            return l.v();
        }
        if (e instanceof Add add) {
            return eval(add.a()) + eval(add.b());
        }
        // Hierarquia fechada — não deveria chegar aqui se todos os permits cobertos
        throw new IllegalStateException();
    }

    /** Playground; descomente no main. */
    static void meuPlayground() {
    }

    public static void main(String[] args) {
        Expr e = new Add(new Lit(2), new Lit(3));
        System.out.println(eval(e));

        // meuPlayground();
    }
}
