/*
 * Copyright 2019 phyzicsz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phyzicsz.rocketdis.codegen.xstream;

import com.phyzicsz.rocketdis.codegen.xstream.converters.DisClassRefConverter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import java.util.Optional;
import javax.lang.model.element.Modifier;

/**
 *
 * @author phyzicsz
 */
@XStreamConverter(DisClassRefConverter.class)
public class DisClassRef extends AbstractAttribute implements AbstractAttributeCodeGeneration{
    
    @XStreamAsAttribute
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public Optional<TypeName> typeName() {
        return Optional.of(ClassName.bestGuess(reference));
    }

    @Override
    public Optional<String> typeSize() {
        return Optional.of(reference + ".wirelineSize()");
    }

    @Override
    public Optional<FieldSpec> fieldSpec() {
        Optional<TypeName> typeCheck  = typeName();
        if(typeCheck.isPresent()){
            TypeName type = typeCheck.get();
            FieldSpec.Builder field = FieldSpec.builder(type, reference)
                    .addModifiers(Modifier.PROTECTED);
            
            if (!type.isPrimitive()) {
                field.initializer("new $T()", type);
            }
            
            return Optional.of(field.build());
        }else{
            return Optional.empty();
        }
        
    }

}
