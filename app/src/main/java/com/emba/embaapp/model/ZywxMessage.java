/**
 *
 */
package com.emba.embaapp.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * @author mikes
 * @version 2015-12-6 上午10:18:24 Note: 与会话关联的本地消息记录
 */
public class ZywxMessage extends DataSupport implements Serializable{


	private long id;// 数据库字段 消息ID

	private String zywxMsgId; //服务端的消息ID,标识消息唯一性的ID
	private String userId;// 用户ID
	private String conversationId;// 会话ID

	private String content;// 文本时 消息内容
	private String messageType;// see ZywxMessageType

	private String remoteUri;// 语音 图片 远程地址 http...
	private String localUri;// 图片 语音 本地地址 file:..
	private String thumbUri;// 图片缩略图地址

	private int duration = 1; //语音消息的时长(s)
	private long fileSize; // 文件大小
	private String fileName; //文件名称

	private String senderUserId; //发送人id
	private String senderUserName; //发送人名称
	private String senderUserIcon; //发送人头像

	private int sentStatus;// see io.rong.imlib.model.Message.SendStatus
	private int receivedStatus;// see io.rong.imlib.model.Message.ReceivedStatus

	private int messageDirection;// 消息方向

	private long sendTime; //发送时间
	private int sum;//消息已读未读
	private long receiveTime;

	private String departName;// 部门名称 群组
	// 时展示

	private boolean isServerFile; //是否是服务器文件


	public int originalImgWidth;// 图片原图宽
	public int originalImgHeight;// 图片原图高

	private String convType;//会话类型
	private boolean isChecked = false; //是否被选中

	public boolean isServerFile() {
		return isServerFile;
	}

	public void setServerFile(boolean isServerFile) {
		this.isServerFile = isServerFile;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZywxMsgId() {
		return zywxMsgId;
	}

	public void setZywxMsgId(String zywxMsgId) {
		this.zywxMsgId = zywxMsgId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getRemoteUri() {
		return remoteUri;
	}

	public void setRemoteUri(String remoteUri) {
		this.remoteUri = remoteUri;
	}

	public String getLocalUri() {
		return localUri;
	}

	public void setLocalUri(String localUri) {
		this.localUri = localUri;
	}

	public String getThumbUri() {
		return thumbUri;
	}

	public void setThumbUri(String thumbUri) {
		this.thumbUri = thumbUri;
	}

	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public String getSenderUserIcon() {
		return senderUserIcon;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public void setSenderUserIcon(String senderUserIcon) {
		this.senderUserIcon = senderUserIcon;
	}

	public int getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(int sentStatus) {
		this.sentStatus = sentStatus;
	}

	public int getReceivedStatus() {
		return receivedStatus;
	}

	public void setReceivedStatus(int receivedStatus) {
		this.receivedStatus = receivedStatus;
	}

	public int getMessageDirection() {
		return messageDirection;
	}

	public void setMessageDirection(int messageDirection) {
		this.messageDirection = messageDirection;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getOriginalImgWidth() {
		return originalImgWidth;
	}

	public void setOriginalImgWidth(int originalImgWidth) {
		this.originalImgWidth = originalImgWidth;
	}

	public int getOriginalImgHeight() {
		return originalImgHeight;
	}

	public void setOriginalImgHeight(int originalImgHeight) {
		this.originalImgHeight = originalImgHeight;
	}

	public String getConvType() {
		return convType;
	}

	public void setConvType(String convType) {
		this.convType = convType;
	}



	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ZywxMessage{" +
				"id=" + id +
				", zywxMsgId='" + zywxMsgId + '\'' +
				", userId='" + userId + '\'' +
				", conversationId='" + conversationId + '\'' +
				", content='" + content + '\'' +
				", messageType='" + messageType + '\'' +
				", remoteUri='" + remoteUri + '\'' +
				", localUri='" + localUri + '\'' +
				", thumbUri='" + thumbUri + '\'' +
				", duration=" + duration +
				", fileSize=" + fileSize +
				", senderUserId='" + senderUserId + '\'' +
				", senderUserName='" + senderUserName + '\'' +
				", senderUserIcon='" + senderUserIcon + '\'' +
				", sentStatus=" + sentStatus +
				", receivedStatus=" + receivedStatus +
				", messageDirection=" + messageDirection +
				", sendTime=" + sendTime +
				", sum=" + sum +
				", receiveTime=" + receiveTime +
				", departName='" + departName + '\'' +
				", isServerFile=" + isServerFile +
				", originalImgWidth=" + originalImgWidth +
				", originalImgHeight=" + originalImgHeight +
				", convType='" + convType + '\'' +
				", isChecked=" + isChecked +
				'}';
	}
}
