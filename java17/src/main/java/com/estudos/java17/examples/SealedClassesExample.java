package com.estudos.java17.examples;

/**
 * PT: sealed + records. Teoria: README.md.
 * EN: sealed types + records. Theory: README.md.
 */
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
        // PT: Hierarquia fechada — não deveria chegar aqui se todos os permits cobertos
        // EN: Closed hierarchy — should not reach here if all permits are covered
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
