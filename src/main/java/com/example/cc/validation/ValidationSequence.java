package com.example.cc.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Sequence for executing validation groups
 *
 */
@GroupSequence({ Default.class, CardNumberValidationGroup.class })
public interface ValidationSequence {
}
