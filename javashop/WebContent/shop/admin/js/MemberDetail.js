var MemberDetail ={
		memberid : undefined,
		init:function(memberid){
			
			var self = this; 
			this.memberid = memberid;
			new Tab(".member_detail");
			this.bindTabEvent();			
			self.showBase();
			
			
		},
		/**
		 * 绑定tab事件
		 */
		bindTabEvent:function(){
			var self = this;
			$("#base").click(function(){ self.showBase(); });
			$("#edit").click(function(){ self.showEdit(); });
			$("#orderLog").click(function(){ self.showOrderLog(); });
			$("#editPoint").click(function(){ self.showEditPoint();   });
			$("#pointLog").click(function(){ self.showPointLog();   });
			$("#advance").click(function(){ self.showAdvance();  });
			$("#ask").click(function(){ self.showAsk();  });
			$("#discuss").click(function(){ self.showDiscuss();  });
			$("#remark").click(function(){ self.showRemark();  });
		},
		
		/**
		 * 显示会员基本信息
		 */
		showBase:function(){
			$("#baseTab").load("member!base.do?ajax=yes&member_id="+this.memberid);
		},
		
		/**
		 * 显示订单货物信息
		 */
		showEdit:function(){
			$("#editTab").load("member!edit.do?ajax=yes&member_id="+this.memberid,function(){
				$('input.dateinput').datepicker();
			});
		},
		
		/**
		 * 显示订单日志
		 */
		showOrderLog:function(){
			$("#orderLogTab").load("member!orderLog.do?ajax=yes&member_id="+this.memberid);
		},
		
		/**
		 * 显示货运日志
		 */
		showEditPoint:function(){
			$("#editPointTab").load("member!editPoint.do?ajax=yes&member_id="+this.memberid);
		},
		
		/**
		 * 显示优惠方案
		 */
		showPointLog:function(){
			$("#pointLogTab").load("member!pointLog.do?ajax=yes&member_id="+this.memberid);
		},
		
		/**
		 * 显示订单日志
		 */
		showAdvance:function(){
			$("#advanceTab").load("member!advance.do?ajax=yes&member_id="+this.memberid);
		},
		
		showAsk:function(){
			$("#askTab").load("member!comments.do?ajax=yes&object_type=ask&member_id="+this.memberid);
		},
		
		showDiscuss:function(){
			$("#discussTab").load("member!comments.do?ajax=yes&object_type=discuss&member_id="+this.memberid);
		}, 
		
		showRemark:function(){
			$("#remarkTab").load("member!remark.do?ajax=yes&member_id="+this.memberid);
		}
};