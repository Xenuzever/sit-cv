package io.sitoolkit.cv.core.domain.uml;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.sitoolkit.cv.core.domain.classdef.ClassDef;
import io.sitoolkit.cv.core.domain.classdef.ClassDefFilter;
import io.sitoolkit.cv.core.domain.classdef.MethodCallDef;
import io.sitoolkit.cv.core.domain.classdef.MethodDef;
import io.sitoolkit.cv.core.infra.config.FilterConditionGroup;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SequenceDiagramProcessor {

    private FilterConditionGroup classFilterGroup;

    public LifeLineDef process(ClassDef clazz, MethodDef method) {
        LifeLineDef lifeLine = new LifeLineDef();
        lifeLine.setSourceId(clazz.getSourceId());
        lifeLine.setEntryMessage(method.getQualifiedSignature());
        lifeLine.setObjectName(clazz.getName());
        lifeLine.setMessages(method.getMethodCalls().stream().map(this::methodCall2Message)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
        lifeLine.setComment(method.getComment());

        log.debug("Add lifeline {} -> {}", clazz.getName(), lifeLine);

        return lifeLine;
    }

    Optional<MessageDef> methodCall2Message(MethodCallDef methodCall) {

        if (methodCall.getClassDef() == null) {
            return Optional.empty();
        }

        MethodDef methodImpl = detectMethodImplementation(methodCall);

        if (!ClassDefFilter.match(methodImpl.getClassDef(), classFilterGroup)) {
            return Optional.empty();
        }

        MessageDef message = new MessageDef();
        message.setRequestName(methodImpl.getSignature());
        message.setRequestQualifiedSignature(methodImpl.getQualifiedSignature());
        message.setTarget(process(methodImpl.getClassDef(), methodImpl));
        message.setResponseName(methodCall.getReturnType().toString());

        return Optional.of(message);

    }

    MethodDef detectMethodImplementation(MethodCallDef methodCall) {

        ClassDef classOrInterface = methodCall.getClassDef();
        if (classOrInterface != null && classOrInterface.isInterface()) {
            ClassDef interfaze = classOrInterface;
            Set<ClassDef> knownImplClasses = interfaze.getKnownImplClasses();
            log.debug("Interface {} has KnownImplements : {}", interfaze.getName(),
                    knownImplClasses);

            if (knownImplClasses.size() == 1) {
                ClassDef onlyImpl = knownImplClasses.iterator().next();
                log.debug("{}'s the only knoun impl found : {} ", interfaze.getName(),
                        onlyImpl.getName());

                Optional<MethodDef> methodImpl = findMethod(onlyImpl, methodCall.getSignature());
                if (methodImpl.isPresent()) {
                    return methodImpl.get();
                }
            }
        }
        return methodCall;
    }

    Optional<MethodDef> findMethod(ClassDef classDef, String signature) {
        log.debug("Finding '{}' from '{}'", signature, classDef.getName());
        Optional<MethodDef> foundMethod = classDef.getMethods().stream()
                .filter(m -> StringUtils.equals(signature, m.getSignature())).findFirst();

        if (foundMethod.isPresent()) {
            log.debug("Method found : {}", foundMethod.get().getQualifiedSignature());
        } else {
            log.debug("'{}' not found from '{}'", signature, classDef.getName());
        }
        return foundMethod;
    }
}
