
public final class Coverage {
  private Coverage() {}

  public static void methodA(boolean a) {
    if (a) {
      System.out.println("methodA:a");
    }
  }

  public static void methodAB(boolean a, boolean b) {
    if (a) {
      System.out.println("methodAB:a");
    }

    if (b) {
      System.out.println("methodAB:b");
    }
  }

  public static void methodABC(boolean a, boolean b, boolean c) {
    if (a) {
      System.out.println("methodABC:a");
    }

    if (b) {
      System.out.println("methodABC:b");
    }

    if (c) {
      System.out.println("methodABC:c");
    }
  }

  public static void methodOrAB(boolean a, boolean b) {
    if (a || b) {
      System.out.println("methodOrAB:a || b");
    } else {
      System.out.println("methodOrAB:!(a || b)");
    }
  }

  public static void methodAndAB(boolean a, boolean b) {
    if (a && b) {
      System.out.println("methodOrAB:a && b");
    } else {
      System.out.println("methodOrAB:!(a && b)");
    }
  }

  public static void methodOrABC(boolean a, boolean b, boolean c) {
    if (a || b || c) {
      System.out.println("methodOrAB:a || b || c");
    } else {
      System.out.println("methodOrAB:!(a || b || c)");
    }
  }

  public static void methodAndABC(boolean a, boolean b, boolean c) {
    if (a && b && c) {
      System.out.println("methodOrAB:a && b && c");
    } else {
      System.out.println("methodOrAB:!(a && b && c)");
    }
  }

  public static void methodOrABCD(boolean a, boolean b, boolean c, boolean d) {
    if (a || b || c || d) {
      System.out.println("methodOrABCD:a || b || c || d");
    } else {
      System.out.println("methodOrABCD:!(a || b || c || d)");
    }
  }

  public static void methodAndABCD(boolean a, boolean b, boolean c, boolean d) {
    if (a && b && c && d) {
      System.out.println("methodAndABCD:a && b && c && d");
    } else {
      System.out.println("methodAndABCD:!(a && b && c && d)");
    }
  }

}
