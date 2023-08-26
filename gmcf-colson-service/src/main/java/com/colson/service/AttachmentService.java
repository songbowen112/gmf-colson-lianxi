package com.colson.service;

import com.colson.service.bean.UserBean;

import java.util.List;

public interface AttachmentService {

    void queryAttachmentList(List<Integer> roundIdList);
}