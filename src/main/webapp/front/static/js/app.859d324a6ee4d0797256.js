webpackJsonp([1],{COVC:function(t,e){},FEYv:function(t,e){},Fx07:function(t,e){},Ls5j:function(t,e){},NHnr:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("7+uW"),i={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]};var n=s("VU/8")({name:"App"},i,!1,function(t){s("v4iE")},null,null).exports,l=s("/ocq"),r=new a.default,o={name:"left",data:function(){return{channelid:"",addchannel:!1,name:"",picid:"",pushUrlOne:"",playUrlOne:"",pushUrlTwo:"",playUrlTwo:"",pushUrlThree:"",playUrlThree:"",imageUrl:"",list:[]}},methods:{add:function(){this.addchannel=!0},handleAvatarSuccess:function(t,e){this.picid=e.response.data.id,this.imageUrl=URL.createObjectURL(e.raw)},beforeAvatarUpload:function(t){console.log(t.type);var e="image/jpeg"===t.type,s="image/png"===t.type,a="image/gif"===t.type,i="image/bmp"===t.type;t.size;if(e||s||a||i)var n=!0;else{n=!1;this.$message.error("上传图片只能是 JPG、PNG、GIF、BMP 格式!")}return n},getList:function(){var t=this;this.list.length=0,this.$http({url:this.$http.adornUrl("/api/channel/list"),method:"POST"}).then(function(e){var s=e.data;t.channelid=s.data[0].id,t.$store.commit("savechannelid",s.data[0].id),t.list=s.data})},changeChanne:function(t){this.channelid=t,this.$store.commit("savechannelid",t)},call:function(){this.name="",this.picid="",this.pushUrlOne="",this.playUrlOne="",this.pushUrlTwo="",this.playUrlTwo="",this.pushUrlThree="",this.playUrlThree="",this.imageUrl="",this.addchannel=!1},save:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/save?name="+this.name+"&picid="+this.picid+"&pushUrlOne="+this.pushUrlOne+"&playUrlOne="+this.playUrlOne+"&pushUrlTwo="+this.pushUrlTwo+"&playUrlTwo="+this.playUrlTwo+"&pushUrlThree="+this.pushUrlThree+"&playUrlThree="+this.playUrlThree),method:"POST"}).then(function(e){var s=e.data;0==s.code?(t.getList(),t.$message({message:"添加频道成功",type:"success",onClose:function(){this.name="",this.picid="",this.pushUrlOne="",this.playUrlOne="",this.pushUrlTwo="",this.playUrlTwo="",this.pushUrlThree="",this.playUrlThree="",this.imageUrl="",this.addchannel=!1}}),t.addchannel=!1):t.$message.error(s.msg)})}},created:function(){var t=this;r.$on("getchannel",function(e){t.getList()})},mounted:function(){this.getList()},components:{},destroyed:function(){}},c={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"left"},[s("div",[s("ul",t._l(t.list,function(e,a){return s("li",{key:a,class:{now:e.id==t.channelid},on:{click:function(s){return t.changeChanne(e.id)}}},[t._v(t._s(e.name))])}),0),t._v(" "),s("button",{staticClass:"add",on:{click:t.add}},[t._v("添加频道")])]),t._v(" "),s("el-dialog",{attrs:{title:"新增频道",visible:t.addchannel,"show-close":!1,"close-on-click-modal":!1},on:{"update:visible":function(e){t.addchannel=e}}},[s("div",{staticClass:"addinputs"},[s("el-row",[s("el-col",{attrs:{span:12}},[t._v("频道名称 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1),t._v(" "),s("el-col",{attrs:{span:12}})],1),t._v(" "),s("el-row",[s("el-col",{attrs:{span:12}},[t._v("推流地址 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.pushUrlOne,callback:function(e){t.pushUrlOne=e},expression:"pushUrlOne"}})],1),t._v(" "),s("el-col",{attrs:{span:12}},[t._v("播放地址 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.playUrlOne,callback:function(e){t.playUrlOne=e},expression:"playUrlOne"}})],1)],1),t._v(" "),s("el-row",[s("el-col",{attrs:{span:12}},[t._v("推流地址 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.pushUrlTwo,callback:function(e){t.pushUrlTwo=e},expression:"pushUrlTwo"}})],1),t._v(" "),s("el-col",{attrs:{span:12}},[t._v("播放地址 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.playUrlTwo,callback:function(e){t.playUrlTwo=e},expression:"playUrlTwo"}})],1)],1),t._v(" "),s("el-row",[s("el-col",{attrs:{span:12}},[t._v("推流地址 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.pushUrlThree,callback:function(e){t.pushUrlThree=e},expression:"pushUrlThree"}})],1),t._v(" "),s("el-col",{attrs:{span:12}},[t._v("播放地址 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.playUrlThree,callback:function(e){t.playUrlThree=e},expression:"playUrlThree"}})],1)],1),t._v(" "),s("el-row",[s("el-col",{attrs:{span:12}},[t._v("\n          水印图片\n          "),t.imageUrl?s("img",{staticClass:"avatar",attrs:{src:t.imageUrl,picid:t.picid}}):s("span",{staticClass:"avatar",staticStyle:{border:"1px solid #d9d9d9"}},[s("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])]),t._v(" "),s("el-col",{attrs:{span:12}},[s("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://localhost:8080/hiswvideoweb_war_exploded/api/pic/upload","show-file-list":!1,"on-success":t.handleAvatarSuccess,"before-upload":t.beforeAvatarUpload}},[s("el-button",{attrs:{size:"small",type:"primary"}},[t._v("更换")])],1)],1)],1)],1),t._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{type:"info",size:"mini"},on:{click:function(e){return t.call()}}},[t._v("取 消")]),t._v(" "),s("el-button",{staticStyle:{"margin-left":"220px"},attrs:{type:"primary",size:"mini"},on:{click:t.save}},[t._v("确 定")])],1)])],1)},staticRenderFns:[]};var p=s("VU/8")(o,c,!1,function(t){s("gbdx"),s("Ls5j")},"data-v-1e292118",null).exports,u={name:"site",data:function(){return{channelid:"",id:"",name:"",picid:"",picHttpUrl:"",pushUrlOne:"",playUrlOne:"",pushUrlTwo:"",playUrlTwo:"",pushUrlThree:"",playUrlThree:""}},computed:{listData:function(){return this.$store.state.channelid}},watch:{listData:function(t){this.channelid=t,this.getdata()}},methods:{handleAvatarSuccess:function(t,e){this.picid=e.response.data.id,this.picHttpUrl=URL.createObjectURL(e.raw)},beforeAvatarUpload:function(t){var e="image/jpeg"===t.type,s="image/png"===t.type,a="image/gif"===t.type,i="image/bmp"===t.type;t.size;if(e||s||a||i)var n=!0;else{n=!1;this.$message.error("上传图片只能是 JPG、PNG、GIF、BMP 格式!")}return n},getdata:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/get?id="+this.channelid),method:"POST"}).then(function(e){var s=e.data;console.log(s);var a=s.data;t.id=a.id,t.name=a.name,t.picid=a.picid,t.picHttpUrl=a.picHttpUrl,t.pushUrlOne=a.pushUrlOne,t.playUrlOne=a.playUrlOne,t.pushUrlTwo=a.pushUrlTwo,t.playUrlTwo=a.playUrlTwo,t.pushUrlThree=a.pushUrlThree,t.playUrlThree=a.playUrlThree})},save:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/update?id="+this.id+"&name="+this.name+"&picid="+this.picid+"&pushUrlOne="+this.pushUrlOne+"&playUrlOne="+this.playUrlOne+"&pushUrlTwo="+this.pushUrlTwo+"&playUrlTwo="+this.playUrlTwo+"&pushUrlThree="+this.pushUrlThree+"&playUrlThree="+this.playUrlThree),method:"POST"}).then(function(e){var s=e.data;0==s.code?(r.$emit("getchannel",{i:1}),t.$message({message:"修改成功",type:"success"})):t.$message.error(s.msg)})}},created:function(){},mounted:function(){},components:{},destroyed:function(){}},d={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"site"},[s("el-row",[s("el-col",{staticClass:"three",attrs:{span:7}},[s("div",[s("span",[t._v("推流地址")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.pushUrlOne,callback:function(e){t.pushUrlOne=e},expression:"pushUrlOne"}})],1),t._v(" "),s("div",[s("span",[t._v("推流地址")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.pushUrlTwo,callback:function(e){t.pushUrlTwo=e},expression:"pushUrlTwo"}})],1),t._v(" "),s("div",[s("span",[t._v("推流地址")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.pushUrlThree,callback:function(e){t.pushUrlThree=e},expression:"pushUrlThree"}})],1)]),t._v(" "),s("el-col",{staticClass:"three",attrs:{span:7}},[s("div",[s("span",[t._v("播放地址")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.playUrlOne,callback:function(e){t.playUrlOne=e},expression:"playUrlOne"}})],1),t._v(" "),s("div",[s("span",[t._v("播放地址")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.playUrlTwo,callback:function(e){t.playUrlTwo=e},expression:"playUrlTwo"}})],1),t._v(" "),s("div",[s("span",[t._v("播放地址")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.playUrlThree,callback:function(e){t.playUrlThree=e},expression:"playUrlThree"}})],1)]),t._v(" "),s("el-col",{staticClass:"watermark",attrs:{span:7}},[s("div",[s("span",[t._v("水印图标")]),t._v(" "),s("img",{attrs:{src:t.picHttpUrl,picid:t.picid,alt:""}})]),t._v(" "),s("div",[s("span",[t._v("频道名称")]),t._v(" "),s("el-input",{staticStyle:{width:"calc(100% - 110px)"},attrs:{channelid:t.id},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1)]),t._v(" "),s("el-col",{staticClass:"btns",attrs:{span:3}},[s("el-upload",{staticClass:"avatar-uploader",staticStyle:{width:"calc(100% - 20px)"},attrs:{action:"http://localhost:8080/hiswvideoweb_war_exploded/api/pic/upload","show-file-list":!1,"on-success":t.handleAvatarSuccess,"before-upload":t.beforeAvatarUpload}},[s("button",{staticStyle:{width:"100%"}},[t._v("更换")])]),t._v(" "),s("button",{on:{click:function(e){return t.save()}}},[t._v("保存")])],1)],1)],1)},staticRenderFns:[]};var h=s("VU/8")(u,d,!1,function(t){s("zbgU"),s("FEYv")},"data-v-ca4a804c",null).exports,v={name:"video",data:function(){return{value:"",options:[],url:"",channelid:"",name:"无",end:"——",nextProgram:"——",continuous:"——",start:"——",type:"——",size:"——",time:"——",rate:"——",resolution:"——"}},computed:{listData:function(){return this.$store.state.channelid}},watch:{listData:function(t){this.channelid=t,this.getchanne(),this.getdata()}},methods:{changechanne:function(){this.ckplayera(this.value)},getchanne:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/get?id="+this.channelid),method:"POST"}).then(function(e){var s=e.data.data;t.value=s.playUrlOne,t.options.push(s.playUrlOne),t.options.push(s.playUrlTwo),t.options.push(s.playUrlThree)})},getdata:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/getLivingProgram"),method:"POST"}).then(function(e){var s=e.data;console.log(s);var a=s.data.livingProgram;if(console.log(a),""==s.data.nextProgram?t.nextProgram="——":t.nextProgram=s.data.nextProgram.name,""==a)t.name="无",t.end="——",t.continuous="——",t.start="——",t.type="——",t.size="——",t.time="——",t.rate="——",t.resolution="——",t.url=!1;else{if(1==a.format)var i="flv";else if(2==a.format)i="m3u8";else if(4==a.format)i="mp4";t.name=a.name,t.end="——",t.continuous=a.duration,t.start=a.startTime,t.type=i,t.size=a.fileSize,t.time=a.duration,t.rate=a.bitRateInformation,t.resolution=a.resolution,t.url=!0,t.ckplayera(t.value)}})},ckplayera:function(t){setTimeout(function(){new ckplayer({container:"#video",variable:"player",loaded:"loadedHandler",autoplay:!1,live:!0,video:t})},2)}},created:function(){},mounted:function(){},components:{},destroyed:function(){}},m={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"video"},[s("el-row",{staticStyle:{"margin-bottom":"10px"}},[t._v("\n    选择播放地址\n    "),s("el-select",{staticStyle:{"margin-left":"20px"},attrs:{size:"mini",placeholder:"请选择"},on:{change:function(e){return t.changechanne()}},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}},t._l(t.options,function(t,e){return s("el-option",{key:e,attrs:{label:t,value:t}})}),1)],1),t._v(" "),s("el-rwo",[s("el-col",{attrs:{span:13}},[0==t.url?s("div",{staticStyle:{width:"100%",height:"420px",background:"#999999"}}):s("div",{attrs:{id:"video"}})]),t._v(" "),s("el-col",{staticStyle:{"padding-left":"30px"},attrs:{span:11}},[s("p",{staticClass:"title"},[t._v("当前直播课程："),s("span",[t._v(t._s(t.name))])]),t._v(" "),s("p",{staticClass:"time"},[s("span",[t._v("当前直播预计结束时间："+t._s(t.end))]),t._v("下场直播："+t._s(t.nextProgram))]),t._v(" "),s("p",{staticClass:"lists"},[s("span",[t._v("持续时长："+t._s(t.continuous))]),t._v("启动时间："+t._s(t.start))]),t._v(" "),s("p",{staticClass:"lists"},[s("span",[t._v("文件类型："+t._s(t.type))]),t._v("文件大小："+t._s(t.size))]),t._v(" "),s("p",{staticClass:"lists"},[s("span",[t._v("文件时间："+t._s(t.time))]),t._v("码率信息："+t._s(t.rate))]),t._v(" "),s("p",{staticClass:"lists"},[s("span",[t._v("分辨率："+t._s(t.resolution))])]),t._v(" "),s("p",{staticClass:"btns"},[s("button",{staticClass:"stop",attrs:{disabled:"disabled"}},[t._v("停止")]),s("button",{staticClass:"next",attrs:{disabled:"disabled"}},[t._v("下一个")])])])],1)],1)},staticRenderFns:[]};var f=s("VU/8")(v,m,!1,function(t){s("orLs")},"data-v-8a709d6a",null).exports,g=s("bOdI"),y=s.n(g),_=s("PJh5"),U=s.n(_),w={name:"list",data:function(){var t;return{courseid:"",channelid:"",selectTiem:new Date,tableData:[],particulars:!1,previews:!1,addSchedule:!1,courseName:"",startupTime:"",courseTime:"",detail:(t={name:"",coverHttpUrl:"",videoid:"",channelId:"",startTime:"",date:""},y()(t,"coverHttpUrl",""),y()(t,"duration",""),y()(t,"videoStatus",""),y()(t,"fileSize",""),y()(t,"filetype",""),y()(t,"bitRateInformation",""),y()(t,"resolution",""),y()(t,"selectstartTime",""),t)}},computed:{listData:function(){return this.$store.state.channelid}},watch:{listData:function(t){console.log(t),this.channelid=t,this.getList()}},methods:{handleAvatarSuccess:function(t,e){console.log(e)},renderHeader:function(t){var e=this;return t("div",[t("el-button",{props:{type:"primary",size:"mini",style:"float: right;"},on:{click:function(){e.addSchedule=!0}}},"新增排片")])},ckplayera:function(){setTimeout(function(){new ckplayer({container:"#video2",variable:"player",loaded:"loadedHandler",autoplay:!1,live:!0,video:"http://img.ksbbs.com/asset/Mon_1703/05cacb4e02f9d9e.mp4"})},2)},previewVideo:function(t){setTimeout(function(){new ckplayer({container:"#video3",variable:"player",loaded:"loadedHandler",autoplay:!1,live:!0,video:t})},2)},details:function(t){var e=this;this.particulars=!0,this.courseid=t,this.$http({url:this.$http.adornUrl("/api/scheduleProgram/get?id="+t),method:"POST"}).then(function(t){var s=t.data;console.log("详情",s),e.detail=s.data,e.detail.selectstartTime=new Date(U()(new Date).format("YYYY-MM-DD")+" "+s.data.startTime)}),this.ckplayera()},preview:function(t){this.previews=!0,this.previewVideo(t)},getList:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/list?channelId="+this.channelid+"&date="+U()(this.selectTiem).format("YYYY-MM-DD")),method:"POST"}).then(function(e){var s=e.data;console.log("1231321213",s),t.tableData=s.data})},savenew:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/save?name="+this.courseName+"&startTime="+U()(this.detail.selectstartTime).format("HH:mm:ss")+"&videoid=&channelId="+this.channelid+"&date="+U()(this.courseTime).format("YYYY-MM-DD")),method:"POST"}).then(function(e){var s=e.data;0==s.code?(t.getList(),t.addSchedule=!1,t.$message({message:"新增排片成功",type:"success"}),t.courseName="",t.startupTime="",t.courseTime=""):t.$message.error(s.msg)})},call:function(){this.courseName="",this.startupTime="",this.courseTime="",this.addSchedule=!1},changecourse:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/update?name="+this.detail.name+"&videoid=&channelId="+this.channelid+"&startTime="+this.detail.startTime+"&id="+this.courseid+"&date="+U()(this.selectTiem).format("YYYY-MM-DD")),method:"POST"}).then(function(e){var s=e.data;0==s.code?(t.getList(),t.particulars=!1,t.$message({message:"修改排片成功",type:"success"})):t.$message.error(s.msg)})},delect:function(t){var e=this;this.$confirm("此操作将删除该课程, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/api/scheduleProgram/delete?id="+t),method:"POST"}).then(function(t){var s=t.data;0==s.code?(e.getList(),e.$message({type:"success",message:"删除成功!"})):e.$message.error(s.msg)})})},start:function(t){var e=this;this.$http({url:"http://edulivetestapi.3xmt.com/transcoding/start/"+t,method:"GET"}).then(function(t){var s=t.data;console.log(s),0==s.code?(e.getList(),e.$message({type:"success",message:"启动成功!"})):e.$message.error(s.msg)})},stop:function(t){var e=this;this.$http({url:"http://edulivetestapi.3xmt.com/transcoding/stop/"+t,method:"GET"}).then(function(t){var s=t.data;console.log(s),0==s.code?(e.getList(),e.$message({type:"success",message:"已停止课程!"})):e.$message.error(s.msg)})}},components:{},created:function(){},mounted:function(){this.getList()},destroyed:function(){}},j={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"list"},[s("div",{staticClass:"date"},[t._v("\n    选择日期\n    "),s("el-date-picker",{staticStyle:{"margin-left":"20px"},attrs:{type:"date",size:"mini",placeholder:"选择日期"},on:{change:function(e){return t.getList()}},model:{value:t.selectTiem,callback:function(e){t.selectTiem=e},expression:"selectTiem"}})],1),t._v(" "),s("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,"header-cell-style":{background:"#F2F2F2"}}},[s("el-table-column",{attrs:{prop:"name",label:"课程名称"}}),t._v(" "),s("el-table-column",{attrs:{prop:"coverHttpUrl",label:"海报封面",width:"180"},scopedSlots:t._u([{key:"default",fn:function(t){return[s("img",{attrs:{src:t.row.coverHttpUrl,alt:"",start:""}})]}}])}),t._v(" "),s("el-table-column",{attrs:{prop:"startTime",label:"启动时间"}}),t._v(" "),s("el-table-column",{attrs:{prop:"length",label:"持续时长"}}),t._v(" "),s("el-table-column",{attrs:{prop:"videoStatus",label:"状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("div",{staticStyle:{"text-align":"right"}},[-1==e.row.videoStatus?s("span",[t._v("等待转码")]):t._e(),t._v(" "),0==e.row.videoStatus?s("span",[t._v("转码中")]):t._e(),t._v(" "),2==e.row.videoStatus?s("span",[t._v("转码失败")]):t._e(),t._v(" "),1==e.row.videoStatus?s("span",[t._v("待直播")]):t._e(),t._v(" "),3==e.row.videoStatus?s("span",[t._v("直播中")]):t._e(),t._v(" "),4==e.row.videoStatus?s("span",[t._v("已直播")]):t._e()])]}}])}),t._v(" "),s("el-table-column",{attrs:{prop:"fileSize",label:"文件大小"}}),t._v(" "),s("el-table-column",{staticStyle:{"text-align":"right"},attrs:{prop:"address","render-header":t.renderHeader,width:"400"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("div",{staticStyle:{"text-align":"right"}},[s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(s){return t.details(e.row.id)}}},[t._v("详情")]),t._v(" "),null==e.row.videoStatus?s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(s){return t.start(e.row.id)}}},[t._v("启动")]):t._e(),t._v(" "),3==e.row.videoStatus?s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(s){return t.stop(e.row.id)}}},[t._v("停止")]):t._e(),t._v(" "),s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(s){return t.preview(e.row.playHttpUrl)}}},[t._v("点播预览")]),t._v(" "),s("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(s){return t.delect(e.row.id)}}},[t._v("删除")])],1)]}}])})],1),t._v(" "),s("el-dialog",{attrs:{title:"详情",visible:t.particulars,width:"760","show-close":!1},on:{"update:visible":function(e){t.particulars=e}}},[s("div",[s("el-row",[s("el-col",{attrs:{span:12}},[t._v("课程名称 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini",placeholder:"请输入内容"},model:{value:t.detail.name,callback:function(e){t.$set(t.detail,"name",e)},expression:"detail.name"}})],1),t._v(" "),s("el-col",{attrs:{span:12}},[t._v("启动时间\n        "),s("el-time-picker",{staticStyle:{"margin-left":"20px"},attrs:{"picker-options":{selectableRange:"00:00:00 - 23:59:59"},size:"mini",placeholder:"选择时间"},on:{change:function(e){return t.changestarttime()}},model:{value:t.detail.selectstartTime,callback:function(e){t.$set(t.detail,"selectstartTime",e)},expression:"detail.selectstartTime"}})],1)],1),t._v(" "),s("img",{staticStyle:{width:"720px",height:"520px"},attrs:{src:t.detail.coverHttpUrl,alt:""}}),t._v(" "),s("el-row",{staticClass:"details-list"},[s("el-col",{attrs:{span:8}},[t._v("持续时长："+t._s(t.detail.length))]),t._v(" "),s("el-col",{attrs:{span:8}},[t._v("启动时间："+t._s(t.detail.startTime))]),t._v(" "),s("el-col",{attrs:{span:8}},[t._v("文件类型："+t._s(t.detail.filetype))])],1),t._v(" "),s("el-row",{staticClass:"details-list"},[s("el-col",{attrs:{span:8}},[t._v("文件大小："+t._s(t.detail.fileSize))]),t._v(" "),s("el-col",{attrs:{span:8}},[t._v("文件时间："+t._s(t.detail.length))]),t._v(" "),s("el-col",{attrs:{span:8}},[t._v("码率信息："+t._s(t.detail.bitRate))])],1),t._v(" "),s("el-row",{staticClass:"details-list"},[s("el-col",{attrs:{span:8}},[t._v("分辨率："+t._s(t.detail.resolution))]),t._v(" "),s("el-col",{attrs:{span:8}}),t._v(" "),s("el-col",{attrs:{span:8}})],1)],1),t._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{type:"info",size:"mini"},on:{click:function(e){t.particulars=!1}}},[t._v("取 消")]),t._v(" "),s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.changecourse()}}},[t._v("保 存")])],1)]),t._v(" "),s("el-dialog",{staticClass:"preview",attrs:{visible:t.previews,width:"760","show-close":!1},on:{"update:visible":function(e){t.previews=e}}},[s("div",{staticStyle:{"text-align":"center",background:"#42BCDB",padding:"0",color:"#ffffff"},attrs:{slot:"title"},slot:"title"},[t._v("\n      点播预览\n      "),s("i",{staticClass:"el-icon-circle-close",staticStyle:{cursor:"pointer",float:"right","font-size":"30px","margin-top":"10px","margin-right":"10px"},on:{click:function(e){t.previews=!1}}})]),t._v(" "),s("div",{attrs:{id:"video3"}})]),t._v(" "),s("el-dialog",{attrs:{title:"新增排片",visible:t.addSchedule,width:"760","show-close":!1,"close-on-click-modal":!1},on:{"update:visible":function(e){t.addSchedule=e}}},[s("div",[s("el-row",[s("el-col",{attrs:{span:12}},[t._v("课程名称 "),s("el-input",{staticStyle:{width:"240px","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.courseName,callback:function(e){t.courseName=e},expression:"courseName"}})],1),t._v(" "),s("el-col",{attrs:{span:12}},[t._v("启动时间 "),s("el-time-picker",{staticStyle:{"margin-left":"20px"},attrs:{"picker-options":{selectableRange:"00:00:00 - 23:59:59"},size:"mini",placeholder:"选择时间"},model:{value:t.startupTime,callback:function(e){t.startupTime=e},expression:"startupTime"}})],1)],1),t._v(" "),s("el-row",{staticStyle:{"margin-top":"20px"}},[s("el-col",{attrs:{span:12}},[t._v("选择日期"),s("el-date-picker",{staticStyle:{width:"240px","margin-left":"23px"},attrs:{size:"mini",type:"date",placeholder:"选择日期"},model:{value:t.courseTime,callback:function(e){t.courseTime=e},expression:"courseTime"}})],1)],1),t._v(" "),s("el-row",{staticStyle:{"margin-top":"20px"}},[s("el-upload",{staticClass:"avatar-uploader",staticStyle:{display:"inline-block"},attrs:{"on-success":t.handleAvatarSuccess,action:"http://eduliveweb.3xmt.com/servlet/uploader?filesize=68487069&projectId=15","show-file-list":!1}},[s("el-button",{attrs:{size:"mini",type:"primary"}},[t._v("上传文件")])],1),t._v(" "),s("el-input",{staticStyle:{width:"80%","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.link,callback:function(e){t.link=e},expression:"link"}})],1)],1),t._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{type:"info",size:"mini"},on:{click:function(e){return t.call()}}},[t._v("取 消")]),t._v(" "),s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.savenew()}}},[t._v("保 存")])],1)])],1)},staticRenderFns:[]};var b={name:"manage",data:function(){return{}},methods:{},created:function(){},mounted:function(){},components:{left:p,site:h,videoChat:f,list:s("VU/8")(w,j,!1,function(t){s("QIkz"),s("COVC")},"data-v-020faf90",null).exports},destroyed:function(){}},T={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"content"},[e("el-row",[e("el-col",{staticStyle:{"min-height":"300px"},attrs:{span:3}},[e("left")],1),this._v(" "),e("el-col",{staticStyle:{"padding-left":"20px"},attrs:{span:21}},[e("site"),this._v(" "),e("videoChat"),this._v(" "),e("list")],1)],1)],1)},staticRenderFns:[]};var x=s("VU/8")(b,T,!1,function(t){s("Fx07")},null,null).exports;a.default.use(l.a);var k=new l.a({base:"history",routes:[{path:"/",name:"manage",component:x}]}),S=s("zL8q"),z=s.n(S),O=(s("tvR6"),s("mtWM")),C=s.n(O),$=s("//Fk"),P=s.n($),F=C.a.create({timeout:3e4,headers:{"Content-Type":"application/json; charset=utf-8"}});F.interceptors.request.use(function(t){return t},function(t){return P.a.reject(t)}),F.interceptors.response.use(function(t){return t},function(t){S.Message.error(response.data.msg)}),F.adornUrl=function(t){return window.SITE_CONFIG.baseUrl+t};var L=F,H=s("NYxO");a.default.use(H.a);var E=new H.a.Store({state:{channelid:""},mutations:{savechannelid:function(t,e){t.channelid=e}}});a.default.use(H.a),a.default.use(z.a),a.default.prototype.axios=C.a,a.default.prototype.$http=L,a.default.config.productionTip=!1,new a.default({el:"#app",router:k,store:E,components:{App:n},template:"<App/>"})},QIkz:function(t,e){},gbdx:function(t,e){},orLs:function(t,e){},tvR6:function(t,e){},uslO:function(t,e,s){var a={"./af":"3CJN","./af.js":"3CJN","./ar":"3MVc","./ar-dz":"tkWw","./ar-dz.js":"tkWw","./ar-kw":"j8cJ","./ar-kw.js":"j8cJ","./ar-ly":"wPpW","./ar-ly.js":"wPpW","./ar-ma":"dURR","./ar-ma.js":"dURR","./ar-sa":"7OnE","./ar-sa.js":"7OnE","./ar-tn":"BEem","./ar-tn.js":"BEem","./ar.js":"3MVc","./az":"eHwN","./az.js":"eHwN","./be":"3hfc","./be.js":"3hfc","./bg":"lOED","./bg.js":"lOED","./bm":"hng5","./bm.js":"hng5","./bn":"aM0x","./bn.js":"aM0x","./bo":"w2Hs","./bo.js":"w2Hs","./br":"OSsP","./br.js":"OSsP","./bs":"aqvp","./bs.js":"aqvp","./ca":"wIgY","./ca.js":"wIgY","./cs":"ssxj","./cs.js":"ssxj","./cv":"N3vo","./cv.js":"N3vo","./cy":"ZFGz","./cy.js":"ZFGz","./da":"YBA/","./da.js":"YBA/","./de":"DOkx","./de-at":"8v14","./de-at.js":"8v14","./de-ch":"Frex","./de-ch.js":"Frex","./de.js":"DOkx","./dv":"rIuo","./dv.js":"rIuo","./el":"CFqe","./el.js":"CFqe","./en-SG":"oYA3","./en-SG.js":"oYA3","./en-au":"Sjoy","./en-au.js":"Sjoy","./en-ca":"Tqun","./en-ca.js":"Tqun","./en-gb":"hPuz","./en-gb.js":"hPuz","./en-ie":"ALEw","./en-ie.js":"ALEw","./en-il":"QZk1","./en-il.js":"QZk1","./en-nz":"dyB6","./en-nz.js":"dyB6","./eo":"Nd3h","./eo.js":"Nd3h","./es":"LT9G","./es-do":"7MHZ","./es-do.js":"7MHZ","./es-us":"INcR","./es-us.js":"INcR","./es.js":"LT9G","./et":"XlWM","./et.js":"XlWM","./eu":"sqLM","./eu.js":"sqLM","./fa":"2pmY","./fa.js":"2pmY","./fi":"nS2h","./fi.js":"nS2h","./fo":"OVPi","./fo.js":"OVPi","./fr":"tzHd","./fr-ca":"bXQP","./fr-ca.js":"bXQP","./fr-ch":"VK9h","./fr-ch.js":"VK9h","./fr.js":"tzHd","./fy":"g7KF","./fy.js":"g7KF","./ga":"U5Iz","./ga.js":"U5Iz","./gd":"nLOz","./gd.js":"nLOz","./gl":"FuaP","./gl.js":"FuaP","./gom-latn":"+27R","./gom-latn.js":"+27R","./gu":"rtsW","./gu.js":"rtsW","./he":"Nzt2","./he.js":"Nzt2","./hi":"ETHv","./hi.js":"ETHv","./hr":"V4qH","./hr.js":"V4qH","./hu":"xne+","./hu.js":"xne+","./hy-am":"GrS7","./hy-am.js":"GrS7","./id":"yRTJ","./id.js":"yRTJ","./is":"upln","./is.js":"upln","./it":"FKXc","./it-ch":"/E8D","./it-ch.js":"/E8D","./it.js":"FKXc","./ja":"ORgI","./ja.js":"ORgI","./jv":"JwiF","./jv.js":"JwiF","./ka":"RnJI","./ka.js":"RnJI","./kk":"j+vx","./kk.js":"j+vx","./km":"5j66","./km.js":"5j66","./kn":"gEQe","./kn.js":"gEQe","./ko":"eBB/","./ko.js":"eBB/","./ku":"kI9l","./ku.js":"kI9l","./ky":"6cf8","./ky.js":"6cf8","./lb":"z3hR","./lb.js":"z3hR","./lo":"nE8X","./lo.js":"nE8X","./lt":"/6P1","./lt.js":"/6P1","./lv":"jxEH","./lv.js":"jxEH","./me":"svD2","./me.js":"svD2","./mi":"gEU3","./mi.js":"gEU3","./mk":"Ab7C","./mk.js":"Ab7C","./ml":"oo1B","./ml.js":"oo1B","./mn":"CqHt","./mn.js":"CqHt","./mr":"5vPg","./mr.js":"5vPg","./ms":"ooba","./ms-my":"G++c","./ms-my.js":"G++c","./ms.js":"ooba","./mt":"oCzW","./mt.js":"oCzW","./my":"F+2e","./my.js":"F+2e","./nb":"FlzV","./nb.js":"FlzV","./ne":"/mhn","./ne.js":"/mhn","./nl":"3K28","./nl-be":"Bp2f","./nl-be.js":"Bp2f","./nl.js":"3K28","./nn":"C7av","./nn.js":"C7av","./pa-in":"pfs9","./pa-in.js":"pfs9","./pl":"7LV+","./pl.js":"7LV+","./pt":"ZoSI","./pt-br":"AoDM","./pt-br.js":"AoDM","./pt.js":"ZoSI","./ro":"wT5f","./ro.js":"wT5f","./ru":"ulq9","./ru.js":"ulq9","./sd":"fW1y","./sd.js":"fW1y","./se":"5Omq","./se.js":"5Omq","./si":"Lgqo","./si.js":"Lgqo","./sk":"OUMt","./sk.js":"OUMt","./sl":"2s1U","./sl.js":"2s1U","./sq":"V0td","./sq.js":"V0td","./sr":"f4W3","./sr-cyrl":"c1x4","./sr-cyrl.js":"c1x4","./sr.js":"f4W3","./ss":"7Q8x","./ss.js":"7Q8x","./sv":"Fpqq","./sv.js":"Fpqq","./sw":"DSXN","./sw.js":"DSXN","./ta":"+7/x","./ta.js":"+7/x","./te":"Nlnz","./te.js":"Nlnz","./tet":"gUgh","./tet.js":"gUgh","./tg":"5SNd","./tg.js":"5SNd","./th":"XzD+","./th.js":"XzD+","./tl-ph":"3LKG","./tl-ph.js":"3LKG","./tlh":"m7yE","./tlh.js":"m7yE","./tr":"k+5o","./tr.js":"k+5o","./tzl":"iNtv","./tzl.js":"iNtv","./tzm":"FRPF","./tzm-latn":"krPU","./tzm-latn.js":"krPU","./tzm.js":"FRPF","./ug-cn":"To0v","./ug-cn.js":"To0v","./uk":"ntHu","./uk.js":"ntHu","./ur":"uSe8","./ur.js":"uSe8","./uz":"XU1s","./uz-latn":"/bsm","./uz-latn.js":"/bsm","./uz.js":"XU1s","./vi":"0X8Q","./vi.js":"0X8Q","./x-pseudo":"e/KL","./x-pseudo.js":"e/KL","./yo":"YXlc","./yo.js":"YXlc","./zh-cn":"Vz2w","./zh-cn.js":"Vz2w","./zh-hk":"ZUyn","./zh-hk.js":"ZUyn","./zh-tw":"BbgG","./zh-tw.js":"BbgG"};function i(t){return s(n(t))}function n(t){var e=a[t];if(!(e+1))throw new Error("Cannot find module '"+t+"'.");return e}i.keys=function(){return Object.keys(a)},i.resolve=n,t.exports=i,i.id="uslO"},v4iE:function(t,e){},zbgU:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.859d324a6ee4d0797256.js.map