// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Person.proto

package com.grpclelarn.models;

public final class PersonOuterClass {
  private PersonOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpclelarn_models_Address_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpclelarn_models_Address_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpclelarn_models_Car_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpclelarn_models_Car_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpclelarn_models_Person_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpclelarn_models_Person_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpclelarn_models_Dealer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpclelarn_models_Dealer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpclelarn_models_Dealer_ModelEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpclelarn_models_Dealer_ModelEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Person.proto\022\025com.grpclelarn.models\"8\n" +
      "\007Address\022\017\n\007postbox\030\001 \001(\005\022\016\n\006street\030\002 \001(" +
      "\t\022\014\n\004city\030\003 \001(\t\"0\n\003Car\022\014\n\004make\030\001 \001(\t\022\r\n\005" +
      "model\030\002 \001(\t\022\014\n\004year\030\003 \001(\005\"}\n\006Person\022\014\n\004n" +
      "ame\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\022/\n\007address\030\003 \001(\0132" +
      "\036.com.grpclelarn.models.Address\022\'\n\003car\030\004" +
      " \003(\0132\032.com.grpclelarn.models.Car\"\213\001\n\006Dea" +
      "ler\0227\n\005model\030\001 \003(\0132(.com.grpclelarn.mode" +
      "ls.Dealer.ModelEntry\032H\n\nModelEntry\022\013\n\003ke" +
      "y\030\001 \001(\005\022)\n\005value\030\002 \001(\0132\032.com.grpclelarn.",
      "models.Car:\0028\001B\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_grpclelarn_models_Address_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_grpclelarn_models_Address_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpclelarn_models_Address_descriptor,
        new java.lang.String[] { "Postbox", "Street", "City", });
    internal_static_com_grpclelarn_models_Car_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_grpclelarn_models_Car_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpclelarn_models_Car_descriptor,
        new java.lang.String[] { "Make", "Model", "Year", });
    internal_static_com_grpclelarn_models_Person_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_grpclelarn_models_Person_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpclelarn_models_Person_descriptor,
        new java.lang.String[] { "Name", "Age", "Address", "Car", });
    internal_static_com_grpclelarn_models_Dealer_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_grpclelarn_models_Dealer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpclelarn_models_Dealer_descriptor,
        new java.lang.String[] { "Model", });
    internal_static_com_grpclelarn_models_Dealer_ModelEntry_descriptor =
      internal_static_com_grpclelarn_models_Dealer_descriptor.getNestedTypes().get(0);
    internal_static_com_grpclelarn_models_Dealer_ModelEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpclelarn_models_Dealer_ModelEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}