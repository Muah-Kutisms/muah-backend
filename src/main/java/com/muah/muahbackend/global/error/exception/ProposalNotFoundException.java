package com.muah.muahbackend.global.error.exception;

import com.muah.muahbackend.global.error.ErrorCode;

public class ProposalNotFoundException extends BusinessException {
    public ProposalNotFoundException() {super(ErrorCode.PROPOSAL_NOT_FOUND);}
}
