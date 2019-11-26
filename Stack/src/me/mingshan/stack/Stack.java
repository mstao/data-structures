package me.mingshan.stack;

/**
 * Stack（FILO）
 */
public interface Stack<E> {

  /**
   * Pushes an item to stack
   *
   * @param item the item
   */
  void push(E item);

  /**
   * Pops the top item of stack
   *
   * @return the pop item
   */
  E pop();

  /**
   * Gets the top item of stack, but not remove this item from stack.
   *
   * @return the top item of stack
   */
  E peek();

  /**
   * Returns the value which the stack is or not empty.
   *
   * @return {@code true} stack is empty; {@code false} stack is not empty.
   */
  boolean isEmpty();

  /**
   * Returns the size of stack
   *
   * @return the size
   */
  int size();

}

