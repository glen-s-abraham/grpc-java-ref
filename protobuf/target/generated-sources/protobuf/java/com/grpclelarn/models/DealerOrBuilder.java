// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Dealer.proto

package com.grpclelarn.models;

public interface DealerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:common.Dealer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;int32, .common.Car&gt; model = 1;</code>
   */
  int getModelCount();
  /**
   * <code>map&lt;int32, .common.Car&gt; model = 1;</code>
   */
  boolean containsModel(
      int key);
  /**
   * Use {@link #getModelMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.Integer, com.grpclelarn.models.Car>
  getModel();
  /**
   * <code>map&lt;int32, .common.Car&gt; model = 1;</code>
   */
  java.util.Map<java.lang.Integer, com.grpclelarn.models.Car>
  getModelMap();
  /**
   * <code>map&lt;int32, .common.Car&gt; model = 1;</code>
   */

  com.grpclelarn.models.Car getModelOrDefault(
      int key,
      com.grpclelarn.models.Car defaultValue);
  /**
   * <code>map&lt;int32, .common.Car&gt; model = 1;</code>
   */

  com.grpclelarn.models.Car getModelOrThrow(
      int key);
}
