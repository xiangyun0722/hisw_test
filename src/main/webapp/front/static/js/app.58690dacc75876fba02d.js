webpackJsonp([1],{"54fu":function(t,e){},"7qmr":function(t,e){},IAAa:function(t,e){},LRhE:function(t,e){},NHnr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("7+uW"),i={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]};var n=a("VU/8")({name:"App"},i,!1,function(t){a("v4iE")},null,null).exports,l=a("/ocq"),r=a("bOdI"),o=a.n(r),c=new s.default,p={name:"left",data:function(){return{templates:"",savechannel:!0,channelid:"",addchannel:!1,name:"",picid:"",pushUrlOne:"",playUrlOne:"",pushUrlTwo:"",playUrlTwo:"",pushUrlThree:"",playUrlThree:"",imageUrl:"",list:[],options:[]}},methods:{gettemplate:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/template/get?projectid=18"),method:"POST"}).then(function(e){var a=e.data;t.options=a.data,console.log(a)})},changeInput:function(){""!==this.name&&""!=this.pushUrlOne&&""!=this.playUrlOne?this.savechannel=!1:this.savechannel=!0},add:function(){this.addchannel=!0},handleAvatarSuccess:function(t,e){this.picid=e.response.data.id,this.imageUrl=URL.createObjectURL(e.raw)},beforeAvatarUpload:function(t){var e="image/jpeg"===t.type,a="image/png"===t.type,s="image/gif"===t.type,i="image/bmp"===t.type;t.size;if(e||a||s||i)var n=!0;else{n=!1;this.$message.error("上传图片只能是 JPG、PNG、GIF、BMP 格式!")}return n},getList:function(){var t=this;this.list.length=0,this.$http({url:this.$http.adornUrl("/api/channel/list"),method:"POST"}).then(function(e){var a=e.data;t.channelid=a.data[0].id,t.$store.commit("savechannelid",a.data[0].id),t.list=a.data})},changeChanne:function(t){this.channelid=t,this.$store.commit("savechannelid",t)},call:function(){this.name="",this.picid="",this.pushUrlOne="",this.playUrlOne="",this.pushUrlTwo="",this.playUrlTwo="",this.pushUrlThree="",this.playUrlThree="",this.imageUrl="",this.addchannel=!1},save:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/save?name="+this.name+"&picid="+this.picid+"&pushUrlOne="+this.pushUrlOne+"&playUrlOne="+this.playUrlOne+"&pushUrlTwo="+this.pushUrlTwo+"&playUrlTwo="+this.playUrlTwo+"&pushUrlThree="+this.pushUrlThree+"&playUrlThree="+this.playUrlThree+"&templateid="+this.templates),method:"POST"}).then(function(e){var a=e.data;0==a.code?(t.getList(),t.$message({message:"添加频道成功",type:"success",onClose:function(){this.name="",this.picid="",this.pushUrlOne="",this.playUrlOne="",this.pushUrlTwo="",this.playUrlTwo="",this.pushUrlThree="",this.playUrlThree="",this.imageUrl="",this.addchannel=!1}}),t.addchannel=!1):t.$message.error(a.msg)})}},created:function(){var t=this;c.$on("getchannel",function(e){t.getList()})},mounted:function(){this.getList(),this.gettemplate()},components:{},destroyed:function(){}},d={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"left"},[a("div",[a("ul",t._l(t.list,function(e,s){return a("li",{key:s,class:{now:e.id==t.channelid},on:{click:function(a){return t.changeChanne(e.id)}}},[t._v(t._s(e.name))])}),0),t._v(" "),a("button",{staticClass:"add",on:{click:t.add}},[t._v("添加频道")])]),t._v(" "),a("el-dialog",{attrs:{title:"新增频道",visible:t.addchannel,"show-close":!1,"close-on-click-modal":!1},on:{"update:visible":function(e){t.addchannel=e}}},[a("div",{staticClass:"addinputs"},[a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%",position:"relative"},attrs:{span:13}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("频道名称 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{onkeyup:"this.value=this.value.replace(/\\s+/g,'')",size:"mini"},on:{input:function(e){return t.changeInput()}},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1),t._v(" "),a("el-col",{attrs:{span:11}},[t._v("\n          模板选择\n          "),a("el-select",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{size:"mini",placeholder:"请选择"},model:{value:t.templates,callback:function(e){t.templates=e},expression:"templates"}},t._l(t.options,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}),1)],1)],1),t._v(" "),a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%",position:"relative"},attrs:{span:13}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("推流地址 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{onkeyup:"this.value=this.value.replace(/\\s+/g,'')",size:"mini"},on:{input:function(e){return t.changeInput()}},model:{value:t.pushUrlOne,callback:function(e){t.pushUrlOne=e},expression:"pushUrlOne"}})],1),t._v(" "),a("el-col",{staticStyle:{position:"relative"},attrs:{span:11}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("播放地址 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{onkeyup:"this.value=this.value.replace(/\\s+/g,'')",size:"mini"},on:{input:function(e){return t.changeInput()}},model:{value:t.playUrlOne,callback:function(e){t.playUrlOne=e},expression:"playUrlOne"}})],1)],1),t._v(" "),a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%"},attrs:{span:13}},[t._v("推流地址 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.pushUrlTwo,callback:function(e){t.pushUrlTwo=e},expression:"pushUrlTwo"}})],1),t._v(" "),a("el-col",{attrs:{span:11}},[t._v("播放地址 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.playUrlTwo,callback:function(e){t.playUrlTwo=e},expression:"playUrlTwo"}})],1)],1),t._v(" "),a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%"},attrs:{span:13}},[t._v("推流地址 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.pushUrlThree,callback:function(e){t.pushUrlThree=e},expression:"pushUrlThree"}})],1),t._v(" "),a("el-col",{attrs:{span:11}},[t._v("播放地址 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{size:"mini"},model:{value:t.playUrlThree,callback:function(e){t.playUrlThree=e},expression:"playUrlThree"}})],1)],1),t._v(" "),a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%"},attrs:{span:13}},[t._v("\n          水印图片\n          "),t.imageUrl?a("img",{staticClass:"avatar",attrs:{src:t.imageUrl,picid:t.picid}}):a("span",{staticClass:"avatar",staticStyle:{border:"1px solid #d9d9d9"}},[a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])]),t._v(" "),a("el-col",{attrs:{span:11}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://localhost:8080/hiswvideoweb_war_exploded/api/pic/upload","show-file-list":!1,"on-success":t.handleAvatarSuccess,"before-upload":t.beforeAvatarUpload}},[a("el-button",{attrs:{size:"small",type:"primary"}},[t._v("更换")])],1)],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-row",[a("el-col",{staticStyle:{height:"10px"},attrs:{span:13}}),t._v(" "),a("el-col",{attrs:{span:11}},[a("el-button",{staticStyle:{float:"left"},attrs:{type:"info",size:"mini"},on:{click:function(e){return t.call()}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary",disabled:t.savechannel,size:"mini"},on:{click:t.save}},[t._v("确 定")])],1)],1)],1)])],1)},staticRenderFns:[]};var u=a("VU/8")(p,d,!1,function(t){a("7qmr"),a("LRhE")},"data-v-f7356c1a",null).exports,h={name:"site",data:function(){return{channelid:"",id:"",name:"",picid:"",picHttpUrl:"",pushUrlOne:"",playUrlOne:"",pushUrlTwo:"",playUrlTwo:"",pushUrlThree:"",playUrlThree:"",options:[],templates:""}},computed:{listData:function(){return this.$store.state.channelid}},watch:{listData:function(t){this.channelid=t,this.getdata()}},methods:{gettemplate:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/template/get?projectid=18"),method:"POST"}).then(function(e){var a=e.data;t.options=a.data,console.log(a)})},handleAvatarSuccess:function(t,e){this.picid=e.response.data.id,this.picHttpUrl=URL.createObjectURL(e.raw)},beforeAvatarUpload:function(t){var e="image/jpeg"===t.type,a="image/png"===t.type,s="image/gif"===t.type,i="image/bmp"===t.type;t.size;if(e||a||s||i)var n=!0;else{n=!1;this.$message.error("上传图片只能是 JPG、PNG、GIF、BMP 格式!")}return n},getdata:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/get?id="+this.channelid),method:"POST"}).then(function(e){var a=e.data.data;t.id=a.id,t.name=a.name,t.picid=a.picid,t.picHttpUrl=a.picHttpUrl,t.pushUrlOne=a.pushUrlOne,t.playUrlOne=a.playUrlOne,t.pushUrlTwo=a.pushUrlTwo,t.playUrlTwo=a.playUrlTwo,t.pushUrlThree=a.pushUrlThree,t.playUrlThree=a.playUrlThree,t.templates=a.templateid})},save:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/update?id="+this.id+"&name="+this.name+"&picid="+this.picid+"&pushUrlOne="+this.pushUrlOne+"&playUrlOne="+this.playUrlOne+"&pushUrlTwo="+this.pushUrlTwo+"&playUrlTwo="+this.playUrlTwo+"&pushUrlThree="+this.pushUrlThree+"&playUrlThree="+this.playUrlThree+"&templateid="+this.templates),method:"POST"}).then(function(e){var a=e.data;0==a.code?(c.$emit("getchannel",{i:1}),t.$message({message:"修改成功",type:"success"})):t.$message.error(a.msg)})}},created:function(){},mounted:function(){this.gettemplate()},components:{},destroyed:function(){}},v={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"site"},[a("el-row",[a("el-col",{staticClass:"three",attrs:{span:7}},[a("div",[a("span",[t._v("推流地址")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.pushUrlOne,callback:function(e){t.pushUrlOne=e},expression:"pushUrlOne"}})],1),t._v(" "),a("div",[a("span",[t._v("推流地址")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.pushUrlTwo,callback:function(e){t.pushUrlTwo=e},expression:"pushUrlTwo"}})],1),t._v(" "),a("div",[a("span",[t._v("推流地址")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.pushUrlThree,callback:function(e){t.pushUrlThree=e},expression:"pushUrlThree"}})],1),t._v(" "),a("div",[a("span",[t._v("频道名称")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},attrs:{channelid:t.id},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1)]),t._v(" "),a("el-col",{staticClass:"three",attrs:{span:7}},[a("div",[a("span",[t._v("播放地址")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.playUrlOne,callback:function(e){t.playUrlOne=e},expression:"playUrlOne"}})],1),t._v(" "),a("div",[a("span",[t._v("播放地址")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.playUrlTwo,callback:function(e){t.playUrlTwo=e},expression:"playUrlTwo"}})],1),t._v(" "),a("div",[a("span",[t._v("播放地址")]),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)"},model:{value:t.playUrlThree,callback:function(e){t.playUrlThree=e},expression:"playUrlThree"}})],1),t._v(" "),a("div",[a("span",[t._v("模板选择")]),t._v(" "),a("el-select",{staticStyle:{width:"calc(100% - 110px)"},attrs:{placeholder:"请选择"},model:{value:t.templates,callback:function(e){t.templates=e},expression:"templates"}},t._l(t.options,function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}),1)],1)]),t._v(" "),a("el-col",{staticClass:"watermark",attrs:{span:7}},[a("div",{staticStyle:{height:""}},[a("span",[t._v("水印图标")]),t._v(" "),a("img",{attrs:{src:t.picHttpUrl,picid:t.picid,alt:""}})])]),t._v(" "),a("el-col",{staticClass:"btns",attrs:{span:3}},[a("el-upload",{staticClass:"avatar-uploader",staticStyle:{width:"calc(100% - 20px)"},attrs:{action:"http://localhost:8080/hiswvideoweb_war_exploded/api/pic/upload","show-file-list":!1,"on-success":t.handleAvatarSuccess,"before-upload":t.beforeAvatarUpload}},[a("button",{staticStyle:{width:"100%"}},[t._v("更换")])]),t._v(" "),a("button",{on:{click:function(e){return t.save()}}},[t._v("保存")])],1)],1)],1)},staticRenderFns:[]};var m=a("VU/8")(h,v,!1,function(t){a("fyLW"),a("54fu")},"data-v-70987add",null).exports,f={name:"videos",data:function(){return{stopdisabled:!0,value:"",options:[],url:"",channelid:"",name:"无",end:"——",nextProgram:"——",continuous:"——",start:"——",type:"——",size:"——",time:"——",rate:"——",resolution:"——",id:"",timing:""}},computed:{listData:function(){return this.$store.state.channelid}},watch:{listData:function(t){this.channelid=t,this.options.length=0,this.getchanne(),this.getdata()}},methods:{changechanne:function(){this.ckplayera(this.value)},getchanne:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/channel/get?id="+this.channelid),method:"POST"}).then(function(e){var a=e.data.data;""!=a.playUrlOne?(t.value=a.playUrlOne,t.options.push(a.playUrlOne)):""!=a.playUrlTwo?t.value=a.playUrlTwo:""!=a.playUrlThree&&(t.value=a.playUrlThree),""!=a.playUrlTwo&&t.options.push(a.playUrlTwo),""!=a.playUrlThree&&t.options.push(a.playUrlThree)})},getdata:function(){var t=this,e=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/getLivingProgram"),method:"POST"}).then(function(a){var s=a.data,i=s.data.livingProgram;if(""==s.data.nextProgram?t.nextProgram="——":t.nextProgram=s.data.nextProgram.name,""==i)setTimeout(function(){e.getdata()},5e3),t.id=null,t.name="无",t.end="——",t.continuous="——",t.start="——",t.type="——",t.size="——",t.time="——",t.rate="——",t.resolution="——",t.url=!1,t.stopdisabled=!0;else{if(1==i.format)var n="flv";else if(2==i.format)n="m3u8";else if(4==i.format)n="mp4";t.id=i.id,t.name=i.name,t.end="——",t.continuous=i.duration,t.start=i.startTime,t.type=n,t.size=i.fileSize/1024/1024+"MB",t.time=i.duration,t.rate=i.bitRateInformation,t.resolution=i.resolution,t.url=!0,t.stopdisabled=!1,t.ckplayera(t.value)}})},stop:function(){var t=this;this.$http({url:"http://edulivetestapi.3xmt.com/transcoding/stop/"+this.id,method:"GET"}).then(function(e){var a=e.data;0==a.code?(t.getdata(),t.$message({type:"success",message:"已停止课程!"})):t.$message.error(a.msg)})},next:function(){var t=this;this.$http({url:"http://edulivetestapi.3xmt.com/transcoding/next/"+this.channelid+"/"+this.id,method:"GET"}).then(function(e){var a=e.data;0==a.code?t.getdata():t.$message.error(a.message)})},ckplayera:function(t){setTimeout(function(){new ckplayer({container:"#video",variable:"player",loaded:"loadedHandler",autoplay:!0,live:!0,video:t})},2)}},created:function(){},mounted:function(){},components:{},destroyed:function(){}},g={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"video"},[a("el-row",{staticStyle:{"margin-bottom":"10px"}},[t._v("\n    选择播放地址\n    "),a("el-select",{staticStyle:{"margin-left":"20px"},attrs:{size:"mini",placeholder:"请选择"},on:{change:function(e){return t.changechanne()}},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}},t._l(t.options,function(t,e){return a("el-option",{key:e,attrs:{label:t,value:t}})}),1)],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:13}},[0==t.url?a("div",{staticStyle:{width:"100%",height:"420px",background:"#999999"}}):a("div",{attrs:{id:"video"}})]),t._v(" "),a("el-col",{staticStyle:{"padding-left":"30px"},attrs:{span:11}},[a("p",{staticClass:"title"},[t._v("当前直播课程："),a("span",[t._v(t._s(t.name))])]),t._v(" "),a("p",{staticClass:"time"},[a("span",[t._v("当前直播预计结束时间："+t._s(t.end))]),t._v("下场直播："+t._s(t.nextProgram))]),t._v(" "),a("p",{staticClass:"lists"},[a("span",[t._v("持续时长："+t._s(t.continuous))]),t._v("启动时间："+t._s(t.start))]),t._v(" "),a("p",{staticClass:"lists"},[a("span",[t._v("文件类型："+t._s(t.type))]),t._v("文件大小："+t._s(t.size))]),t._v(" "),a("p",{staticClass:"lists"},[a("span",[t._v("文件时间："+t._s(t.time))]),t._v("码率信息："+t._s(t.rate))]),t._v(" "),a("p",{staticClass:"lists"},[a("span",[t._v("分辨率："+t._s(t.resolution))])]),t._v(" "),a("p",{staticClass:"btns"},[a("button",{staticClass:"stop",attrs:{disabled:t.stopdisabled},on:{click:function(e){return t.stop()}}},[t._v("停止")]),a("button",{staticClass:"next",on:{click:function(e){return t.next()}}},[t._v("下一个")])])])],1)],1)},staticRenderFns:[]};var y=a("VU/8")(f,g,!1,function(t){a("IAAa")},"data-v-4fbf1952",null).exports,_=a("PJh5"),w=a.n(_),U={name:"list",data:function(){var t;return{url:"",courseid:"",channelid:"",selectTiem:new Date,tableData:[],particulars:!1,previews:!1,addSchedule:!1,courseName:"",startupTime:"",courseTime:new Date,filename:"",filesize:"",videoFlag:!1,adddisabled:!0,videoUploadPercent:"",videoname:"",videoForm:{Video:""},newvideoid:"",detail:(t={name:"",coverHttpUrl:"",videoid:"",channelId:"",startTime:"",date:""},o()(t,"coverHttpUrl",""),o()(t,"duration",""),o()(t,"videoStatus",""),o()(t,"fileSize",""),o()(t,"filetype",""),o()(t,"bitRateInformation",""),o()(t,"resolution",""),o()(t,"selectstartTime",""),o()(t,"format",""),t)}},computed:{listData:function(){return this.$store.state.channelid}},watch:{listData:function(t){this.channelid=t,this.getList()}},methods:{inputchange:function(){""!=this.courseName&&""!=this.startupTime&&""!=this.courseTime&&""!=this.videoname?this.adddisabled=!1:this.adddisabled=!0},handleUpload:function(t){var e=this,a=t.file,s=a.size,i=a.name,n=new FormData;n.append("file",a);var l=this;this.$http({url:"http://localhost:8080/hiswvideoweb_war_exploded/uploadOnLine/upload?filesize="+s+"&projectId=22&name="+i,method:"POST",data:n,onUploadProgress:function(t){var e=t.loaded/t.total*100|0;l.videoFlag=!0,e<100&&(l.adddisabled=!0),l.videoUploadPercent=e}}).then(function(t){var a=t.data;1==a.status?(e.videoname=a.filePath,e.newvideoid=a.vid,e.inputchange()):(e.$message.error("上传失败"),e.inputchange())})},renderHeader:function(t){var e=this;return t("div",[t("el-button",{props:{type:"primary",size:"mini"},style:"float:right",on:{click:function(){e.addSchedule=!0}}},"新增排片")])},ckplayera:function(){setTimeout(function(){new ckplayer({container:"#video2",variable:"player",loaded:"loadedHandler",autoplay:!1,live:!0,video:"http://img.ksbbs.com/asset/Mon_1703/05cacb4e02f9d9e.mp4"})},2)},previewVideo:function(t){console.log(t),setTimeout(function(){new ckplayer({container:"#video3",variable:"player",loaded:"loadedHandler",autoplay:!1,live:!1,video:t})},2)},details:function(t){var e=this;this.particulars=!0,this.courseid=t,this.$http({url:this.$http.adornUrl("/api/scheduleProgram/get?id="+t),method:"POST"}).then(function(t){var a=t.data;e.detail=a.data,e.detail.selectstartTime=new Date(w()(new Date).format("YYYY-MM-DD")+" "+a.data.startTime)}),this.ckplayera()},preview:function(t){this.previews=!0,this.previewVideo(t)},getList:function(){var t=this,e=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/list?channelId="+this.channelid+"&date="+w()(this.selectTiem).format("YYYY-MM-DD")),method:"POST"}).then(function(a){var s=a.data;if(console.log(s.data),setTimeout(function(){e.getList()},5e3),void 0!=s.data)for(var i=0;i<s.data.length;i++)if(console.log(s.data[i].length),void 0!=s.data[i].length){var n=t.formatSeconds(s.data[i].length);s.data[i].length=n}t.tableData=s.data})},formatSeconds:function(t){var e=parseInt(t),a=0;return a=parseInt(e/60),e=parseInt(e%60),a.toString().padStart(2,"0")+":"+e.toString().padStart(2,"0")},savenew:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/save?name="+this.courseName+"&startTime="+w()(this.startupTime).format("HH:mm:ss")+"&videoid="+this.newvideoid+"&channelId="+this.channelid+"&date="+w()(this.courseTime).format("YYYY-MM-DD")),method:"POST"}).then(function(e){var a=e.data;0==a.code?(t.getList(),t.addSchedule=!1,t.$message({message:"新增排片成功",type:"success"}),t.videoUploadPercent="",t.videoFlag=!1,t.videoname="",t.newvideoid="",t.courseName="",t.startupTime="",t.courseTime=new Date):t.$message.error(a.msg)})},call:function(){this.videoname="",this.newvideoid="",this.courseName="",this.startupTime="",this.courseTime=new Date,this.addSchedule=!1,this.videoUploadPercent="",this.videoFlag=!1},changecourse:function(){var t=this;this.$http({url:this.$http.adornUrl("/api/scheduleProgram/update?name="+this.detail.name+"&videoid=&channelId="+this.channelid+"&startTime="+this.detail.startTime+"&id="+this.courseid+"&date="+w()(this.selectTiem).format("YYYY-MM-DD")),method:"POST"}).then(function(e){var a=e.data;0==a.code?(t.getList(),t.particulars=!1,t.$message({message:"修改排片成功",type:"success"})):t.$message.error(a.msg)})},delect:function(t){var e=this;this.$confirm("此操作将删除该课程, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/api/scheduleProgram/delete?id="+t),method:"POST"}).then(function(t){var a=t.data;0==a.code?(e.getList(),e.$message({type:"success",message:"删除成功!"})):e.$message.error(a.msg)})})},start:function(t){var e=this;this.$http({url:"http://edulivetestapi.3xmt.com/transcoding/start/"+t,method:"GET"}).then(function(t){var a=t.data;0==a.code?(e.getList(),e.$message({type:"success",message:"启动成功!"}),e.$emit("changevideo")):e.$message.error(a.msg)})},stop:function(t){var e=this;this.$http({url:"http://edulivetestapi.3xmt.com/transcoding/stop/"+t,method:"GET"}).then(function(t){var a=t.data;0==a.code?(e.getList(),e.$message({type:"success",message:"已停止课程!"}),e.$emit("changevideo")):e.$message.error(a.msg)})}},components:{},created:function(){},mounted:function(){this.getList()},destroyed:function(){}},b={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"list"},[a("div",{staticClass:"date"},[t._v("\n    选择日期\n    "),a("el-date-picker",{staticStyle:{"margin-left":"20px"},attrs:{type:"date",size:"mini",placeholder:"选择日期"},on:{change:function(e){return t.getList()}},model:{value:t.selectTiem,callback:function(e){t.selectTiem=e},expression:"selectTiem"}})],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,"header-cell-style":{background:"#F2F2F2"}}},[a("el-table-column",{attrs:{prop:"name",label:"课程名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"coverHttpUrl",label:"海报封面",width:"180"},scopedSlots:t._u([{key:"default",fn:function(t){return[a("img",{attrs:{src:t.row.coverHttpUrl,alt:"",start:""}})]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"startTime",label:"启动时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"length",label:"持续时长"}}),t._v(" "),a("el-table-column",{attrs:{prop:"videoStatus",label:"状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("div",[-1==e.row.videoStatus?a("span",[t._v("等待转码")]):t._e(),t._v(" "),0==e.row.videoStatus?a("span",[t._v("转码中")]):t._e(),t._v(" "),2==e.row.videoStatus?a("span",[t._v("转码失败")]):t._e(),t._v(" "),1==e.row.videoStatus?a("span",[t._v("待直播")]):t._e(),t._v(" "),3==e.row.videoStatus?a("span",[t._v("直播中")]):t._e(),t._v(" "),4==e.row.videoStatus?a("span",[t._v("已直播")]):t._e(),t._v(" "),5==e.row.videoStatus?a("span",[t._v("已停止")]):t._e()])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"fileSize",label:"文件大小"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.fileSize>1048575?a("div",[t._v("\n          "+t._s(e.row.fileSize/1024/1024)+"MB\n        ")]):e.row.fileSize>1023?a("div",[t._v("\n          "+t._s(e.row.fileSize/1024)+"KB\n        ")]):e.row.fileSize>0?a("div",[t._v("\n          "+t._s(e.row.fileSize)+"字节\n        ")]):a("div",[t._v(t._s(e.row.fileSize))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{"text-align":"right"},attrs:{prop:"address","render-header":t.renderHeader,width:"400"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("div",{staticStyle:{"text-align":"right"}},[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return t.details(e.row.id)}}},[t._v("详情")]),t._v(" "),1==e.row.videoStatus||4==e.row.videoStatus||5==e.row.videoStatus?a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return t.start(e.row.id)}}},[t._v("启动")]):t._e(),t._v(" "),3==e.row.videoStatus?a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return t.stop(e.row.id)}}},[t._v("停止")]):t._e(),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return t.preview(e.row.playHttpUrl)}}},[t._v("点播预览")]),t._v(" "),a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(a){return t.delect(e.row.id)}}},[t._v("删除")])],1)]}}])})],1),t._v(" "),a("el-dialog",{attrs:{title:"详情",visible:t.particulars,width:"760","show-close":!1},on:{"update:visible":function(e){t.particulars=e}}},[a("div",[a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%","margin-bottom":"20px"},attrs:{span:13}},[t._v("课程名称： "+t._s(t.detail.name)+"\n        ")]),t._v(" "),a("el-col",{attrs:{span:11}},[t._v("启动时间： "+t._s(t.detail.startTime)+"\n          ")])],1),t._v(" "),a("el-row",{staticClass:"details-list"},[a("el-col",{attrs:{span:8}},[t._v("持续时长："+t._s(t.detail.length))]),t._v(" "),a("el-col",{attrs:{span:8}},[t._v("启动时间："+t._s(t.detail.startTime))]),t._v(" "),1==t.detail.format?a("el-col",{attrs:{span:8}},[t._v("文件类型：flv")]):t._e(),t._v(" "),2==t.detail.format?a("el-col",{attrs:{span:8}},[t._v("文件类型：m3u8")]):t._e(),t._v(" "),4==t.detail.format?a("el-col",{attrs:{span:8}},[t._v("文件类型：mp4")]):t._e()],1),t._v(" "),a("el-row",{staticClass:"details-list"},[a("el-col",{attrs:{span:8}},[t._v("文件大小："+t._s(t.detail.fileSize))]),t._v(" "),a("el-col",{attrs:{span:8}},[t._v("文件时间："+t._s(t.detail.length))]),t._v(" "),a("el-col",{attrs:{span:8}},[t._v("码率信息："+t._s(t.detail.bitRate))])],1),t._v(" "),a("el-row",{staticClass:"details-list"},[a("el-col",{attrs:{span:8}},[t._v("分辨率："+t._s(t.detail.resolution))]),t._v(" "),a("el-col",{attrs:{span:8}}),t._v(" "),a("el-col",{attrs:{span:8}})],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"info",size:"mini"},on:{click:function(e){t.particulars=!1}}},[t._v("取 消")])],1)]),t._v(" "),a("el-dialog",{staticClass:"preview",attrs:{visible:t.previews,width:"760","show-close":!1},on:{"update:visible":function(e){t.previews=e}}},[a("div",{staticStyle:{"text-align":"center",background:"#42BCDB",padding:"0",color:"#ffffff"},attrs:{slot:"title"},slot:"title"},[t._v("\n      点播预览\n      "),a("i",{staticClass:"el-icon-circle-close",staticStyle:{cursor:"pointer",float:"right","font-size":"30px","margin-top":"10px","margin-right":"10px"},on:{click:function(e){t.previews=!1}}})]),t._v(" "),a("div",{attrs:{id:"video3"}})]),t._v(" "),a("el-dialog",{attrs:{title:"新增排片",visible:t.addSchedule,width:"760","show-close":!1,"close-on-click-modal":!1},on:{"update:visible":function(e){t.addSchedule=e}}},[a("div",[a("el-row",[a("el-col",{staticStyle:{"padding-right":"8.32%"},attrs:{span:13}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("课程名称 "),a("el-input",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{onkeyup:"this.value=this.value.replace(/\\s+/g,'')",size:"mini"},on:{input:t.inputchange},model:{value:t.courseName,callback:function(e){t.courseName=e},expression:"courseName"}})],1),t._v(" "),a("el-col",{staticStyle:{position:"relative"},attrs:{span:11}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("启动时间 "),a("el-time-picker",{staticStyle:{width:"calc(100% - 81px)","margin-left":"20px"},attrs:{"picker-options":{selectableRange:"00:00:00 - 23:59:59"},size:"mini",placeholder:"选择时间"},on:{change:t.inputchange},model:{value:t.startupTime,callback:function(e){t.startupTime=e},expression:"startupTime"}})],1)],1),t._v(" "),a("el-row",{staticStyle:{"margin-top":"20px"}},[a("el-col",{staticStyle:{"padding-right":"8.32%"},attrs:{span:13}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("选择日期"),a("el-date-picker",{staticStyle:{width:"calc(100% - 81px)","margin-left":"23px"},attrs:{size:"mini",type:"date",placeholder:"选择日期"},on:{change:t.inputchange},model:{value:t.courseTime,callback:function(e){t.courseTime=e},expression:"courseTime"}})],1)],1),t._v(" "),a("el-row",{staticStyle:{"margin-top":"20px"}},[a("el-upload",{staticClass:"avatar-uploader",staticStyle:{display:"inline-block"},attrs:{action:"string","show-file-list":!1,"http-request":t.handleUpload,accept:".mp4,.flv,.avi,.wmv,.rmvb"}},[a("el-button",{attrs:{size:"mini",type:"primary"}},[a("span",{staticStyle:{color:"red",position:"absolute",left:"-10px",top:"5px"}},[t._v("*")]),t._v("上传文件")])],1),t._v(" "),a("el-input",{staticStyle:{width:"calc(100% - 110px)","margin-left":"20px"},attrs:{size:"mini",disabled:"disabled"},model:{value:t.videoname,callback:function(e){t.videoname=e},expression:"videoname"}})],1),t._v(" "),a("el-row",[1==t.videoFlag?a("el-progress",{staticStyle:{"margin-top":"30px"},attrs:{percentage:t.videoUploadPercent}}):t._e()],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"info",size:"mini"},on:{click:function(e){return t.call()}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini",disabled:t.adddisabled},on:{click:function(e){return t.savenew()}}},[t._v("保 存")])],1)])],1)},staticRenderFns:[]};var j,T=(j={name:"manage",data:function(){return{}},methods:{},created:function(){},mounted:function(){},components:{left:u,site:m,videoChat:y,list:a("VU/8")(U,b,!1,function(t){a("tBkI"),a("VM+O")},"data-v-e2c219a0",null).exports}},o()(j,"methods",{changevideo:function(){this.$refs.borther[0].getdata()}}),o()(j,"destroyed",function(){}),j),x={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"content"},[e("el-row",[e("el-col",{staticStyle:{"min-height":"300px"},attrs:{span:3}},[e("left")],1),this._v(" "),e("el-col",{staticStyle:{"padding-left":"20px"},attrs:{span:21}},[e("site"),this._v(" "),e("videoChat",{ref:"borther"}),this._v(" "),e("list")],1)],1)],1)},staticRenderFns:[]};var S=a("VU/8")(T,x,!1,function(t){a("m8bw")},null,null).exports;s.default.use(l.a);var k=new l.a({base:"history",routes:[{path:"/",name:"manage",component:S}]}),z=a("zL8q"),O=a.n(z),$=(a("tvR6"),a("mtWM")),P=a.n($),C=a("//Fk"),F=a.n(C),L=P.a.create({headers:{"Content-Type":"application/json; charset=utf-8"}});L.interceptors.request.use(function(t){return t},function(t){return F.a.reject(t)}),L.interceptors.response.use(function(t){return t},function(t){z.Message.error(response.data.msg)}),L.adornUrl=function(t){return window.SITE_CONFIG.baseUrl+t};var D=L,I=a("NYxO");s.default.use(I.a);var E=new I.a.Store({state:{channelid:""},mutations:{savechannelid:function(t,e){t.channelid=e}}});s.default.use(I.a),s.default.use(O.a),s.default.prototype.axios=P.a,s.default.prototype.$http=D,s.default.config.productionTip=!1,new s.default({el:"#app",router:k,store:E,components:{App:n},template:"<App/>"})},"VM+O":function(t,e){},fyLW:function(t,e){},m8bw:function(t,e){},tBkI:function(t,e){},tvR6:function(t,e){},uslO:function(t,e,a){var s={"./af":"3CJN","./af.js":"3CJN","./ar":"3MVc","./ar-dz":"tkWw","./ar-dz.js":"tkWw","./ar-kw":"j8cJ","./ar-kw.js":"j8cJ","./ar-ly":"wPpW","./ar-ly.js":"wPpW","./ar-ma":"dURR","./ar-ma.js":"dURR","./ar-sa":"7OnE","./ar-sa.js":"7OnE","./ar-tn":"BEem","./ar-tn.js":"BEem","./ar.js":"3MVc","./az":"eHwN","./az.js":"eHwN","./be":"3hfc","./be.js":"3hfc","./bg":"lOED","./bg.js":"lOED","./bm":"hng5","./bm.js":"hng5","./bn":"aM0x","./bn.js":"aM0x","./bo":"w2Hs","./bo.js":"w2Hs","./br":"OSsP","./br.js":"OSsP","./bs":"aqvp","./bs.js":"aqvp","./ca":"wIgY","./ca.js":"wIgY","./cs":"ssxj","./cs.js":"ssxj","./cv":"N3vo","./cv.js":"N3vo","./cy":"ZFGz","./cy.js":"ZFGz","./da":"YBA/","./da.js":"YBA/","./de":"DOkx","./de-at":"8v14","./de-at.js":"8v14","./de-ch":"Frex","./de-ch.js":"Frex","./de.js":"DOkx","./dv":"rIuo","./dv.js":"rIuo","./el":"CFqe","./el.js":"CFqe","./en-SG":"oYA3","./en-SG.js":"oYA3","./en-au":"Sjoy","./en-au.js":"Sjoy","./en-ca":"Tqun","./en-ca.js":"Tqun","./en-gb":"hPuz","./en-gb.js":"hPuz","./en-ie":"ALEw","./en-ie.js":"ALEw","./en-il":"QZk1","./en-il.js":"QZk1","./en-nz":"dyB6","./en-nz.js":"dyB6","./eo":"Nd3h","./eo.js":"Nd3h","./es":"LT9G","./es-do":"7MHZ","./es-do.js":"7MHZ","./es-us":"INcR","./es-us.js":"INcR","./es.js":"LT9G","./et":"XlWM","./et.js":"XlWM","./eu":"sqLM","./eu.js":"sqLM","./fa":"2pmY","./fa.js":"2pmY","./fi":"nS2h","./fi.js":"nS2h","./fo":"OVPi","./fo.js":"OVPi","./fr":"tzHd","./fr-ca":"bXQP","./fr-ca.js":"bXQP","./fr-ch":"VK9h","./fr-ch.js":"VK9h","./fr.js":"tzHd","./fy":"g7KF","./fy.js":"g7KF","./ga":"U5Iz","./ga.js":"U5Iz","./gd":"nLOz","./gd.js":"nLOz","./gl":"FuaP","./gl.js":"FuaP","./gom-latn":"+27R","./gom-latn.js":"+27R","./gu":"rtsW","./gu.js":"rtsW","./he":"Nzt2","./he.js":"Nzt2","./hi":"ETHv","./hi.js":"ETHv","./hr":"V4qH","./hr.js":"V4qH","./hu":"xne+","./hu.js":"xne+","./hy-am":"GrS7","./hy-am.js":"GrS7","./id":"yRTJ","./id.js":"yRTJ","./is":"upln","./is.js":"upln","./it":"FKXc","./it-ch":"/E8D","./it-ch.js":"/E8D","./it.js":"FKXc","./ja":"ORgI","./ja.js":"ORgI","./jv":"JwiF","./jv.js":"JwiF","./ka":"RnJI","./ka.js":"RnJI","./kk":"j+vx","./kk.js":"j+vx","./km":"5j66","./km.js":"5j66","./kn":"gEQe","./kn.js":"gEQe","./ko":"eBB/","./ko.js":"eBB/","./ku":"kI9l","./ku.js":"kI9l","./ky":"6cf8","./ky.js":"6cf8","./lb":"z3hR","./lb.js":"z3hR","./lo":"nE8X","./lo.js":"nE8X","./lt":"/6P1","./lt.js":"/6P1","./lv":"jxEH","./lv.js":"jxEH","./me":"svD2","./me.js":"svD2","./mi":"gEU3","./mi.js":"gEU3","./mk":"Ab7C","./mk.js":"Ab7C","./ml":"oo1B","./ml.js":"oo1B","./mn":"CqHt","./mn.js":"CqHt","./mr":"5vPg","./mr.js":"5vPg","./ms":"ooba","./ms-my":"G++c","./ms-my.js":"G++c","./ms.js":"ooba","./mt":"oCzW","./mt.js":"oCzW","./my":"F+2e","./my.js":"F+2e","./nb":"FlzV","./nb.js":"FlzV","./ne":"/mhn","./ne.js":"/mhn","./nl":"3K28","./nl-be":"Bp2f","./nl-be.js":"Bp2f","./nl.js":"3K28","./nn":"C7av","./nn.js":"C7av","./pa-in":"pfs9","./pa-in.js":"pfs9","./pl":"7LV+","./pl.js":"7LV+","./pt":"ZoSI","./pt-br":"AoDM","./pt-br.js":"AoDM","./pt.js":"ZoSI","./ro":"wT5f","./ro.js":"wT5f","./ru":"ulq9","./ru.js":"ulq9","./sd":"fW1y","./sd.js":"fW1y","./se":"5Omq","./se.js":"5Omq","./si":"Lgqo","./si.js":"Lgqo","./sk":"OUMt","./sk.js":"OUMt","./sl":"2s1U","./sl.js":"2s1U","./sq":"V0td","./sq.js":"V0td","./sr":"f4W3","./sr-cyrl":"c1x4","./sr-cyrl.js":"c1x4","./sr.js":"f4W3","./ss":"7Q8x","./ss.js":"7Q8x","./sv":"Fpqq","./sv.js":"Fpqq","./sw":"DSXN","./sw.js":"DSXN","./ta":"+7/x","./ta.js":"+7/x","./te":"Nlnz","./te.js":"Nlnz","./tet":"gUgh","./tet.js":"gUgh","./tg":"5SNd","./tg.js":"5SNd","./th":"XzD+","./th.js":"XzD+","./tl-ph":"3LKG","./tl-ph.js":"3LKG","./tlh":"m7yE","./tlh.js":"m7yE","./tr":"k+5o","./tr.js":"k+5o","./tzl":"iNtv","./tzl.js":"iNtv","./tzm":"FRPF","./tzm-latn":"krPU","./tzm-latn.js":"krPU","./tzm.js":"FRPF","./ug-cn":"To0v","./ug-cn.js":"To0v","./uk":"ntHu","./uk.js":"ntHu","./ur":"uSe8","./ur.js":"uSe8","./uz":"XU1s","./uz-latn":"/bsm","./uz-latn.js":"/bsm","./uz.js":"XU1s","./vi":"0X8Q","./vi.js":"0X8Q","./x-pseudo":"e/KL","./x-pseudo.js":"e/KL","./yo":"YXlc","./yo.js":"YXlc","./zh-cn":"Vz2w","./zh-cn.js":"Vz2w","./zh-hk":"ZUyn","./zh-hk.js":"ZUyn","./zh-tw":"BbgG","./zh-tw.js":"BbgG"};function i(t){return a(n(t))}function n(t){var e=s[t];if(!(e+1))throw new Error("Cannot find module '"+t+"'.");return e}i.keys=function(){return Object.keys(s)},i.resolve=n,t.exports=i,i.id="uslO"},v4iE:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.58690dacc75876fba02d.js.map