/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.glutenproject.substrait.type;

import io.substrait.proto.Type;

import java.io.Serializable;

public class FixedCharTypeNode implements TypeNode, Serializable {
  private final Boolean nullable;
  private final int length;

  FixedCharTypeNode(Boolean nullable, int length) {
    this.nullable = nullable;
    this.length = length;
  }

  @Override
  public Type toProtobuf() {
    Type.FixedChar.Builder fixedCharBuilder = Type.FixedChar.newBuilder();
    if (nullable) {
      fixedCharBuilder.setNullability(Type.Nullability.NULLABILITY_NULLABLE);
    } else {
      fixedCharBuilder.setNullability(Type.Nullability.NULLABILITY_REQUIRED);
    }
    fixedCharBuilder.setLength(length);

    Type.Builder builder = Type.newBuilder();
    builder.setFixedChar(fixedCharBuilder.build());
    return builder.build();
  }
}
