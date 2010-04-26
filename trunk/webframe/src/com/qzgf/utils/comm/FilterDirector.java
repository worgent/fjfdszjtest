package com.qzgf.utils.comm;


public class FilterDirector {

  /**
   * Reference to Builder currently used
   */
  private FilterBuilder builder;

  /**
   * Create a new <code>FilterDirector</code> instance.
   *
   * @param builder the builder which will create the product
   */
  public FilterDirector(FilterBuilder builder) {
    this.builder = builder;
  }

  /**
   * Construct the Product using the Builder.
   */
  public void construct() {
    builder.buildFilter();
  }

}
