(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-00143a5e"],{"1f4f":function(e,t,n){"use strict";n("8b37");var i=n("80d2"),s=n("7560"),a=n("58df");t["a"]=Object(a["a"])(s["a"]).extend({name:"v-simple-table",props:{dense:Boolean,fixedHeader:Boolean,height:[Number,String]},computed:{classes(){return{"v-data-table--dense":this.dense,"v-data-table--fixed-height":!!this.height&&!this.fixedHeader,"v-data-table--fixed-header":this.fixedHeader,...this.themeClasses}}},methods:{genWrapper(){return this.$slots.wrapper||this.$createElement("div",{staticClass:"v-data-table__wrapper",style:{height:Object(i["h"])(this.height)}},[this.$createElement("table",this.$slots.default)])}},render(e){return e("div",{staticClass:"v-data-table",class:this.classes},[this.$slots.top,this.genWrapper(),this.$slots.bottom])}})},"8b37":function(e,t,n){},bb51:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("v-simple-table",{attrs:{dense:""},scopedSlots:e._u([{key:"default",fn:function(){return[n("thead",[n("tr",[n("th",[e._v("EntryPoint")])])]),n("tbody",e._l(e.entryPoints,(function(t){return n("tr",[n("td",[n("router-link",{attrs:{to:t.url}},[e._v(e._s(t.id))])],1)])})),0)]},proxy:!0}])})},s=[],a=(n("ac6a"),n("d225")),r=n("b0b4"),l=n("308d"),c=n("6bb5"),o=n("4e2b"),d=n("9ab4"),u=n("60a3"),h=n("9ce9"),b=function e(t,n){Object(a["a"])(this,e),this.id=t,this.url=n},f=function(e){function t(){var e;return Object(a["a"])(this,t),e=Object(l["a"])(this,Object(c["a"])(t).apply(this,arguments)),e.designDocService=h["a"].getService(),e.entryPoints=[],e}return Object(o["a"])(t,e),Object(r["a"])(t,[{key:"created",value:function(){var e=this;this.designDocService.fetchMenuItems((function(t){return e.menuItemToEntryPointRecursively(t)}))}},{key:"menuItemToEntryPointRecursively",value:function(e){var t=this;e.forEach((function(e){if("crud-matrix"!=e.id)if(e.children.length>0)t.menuItemToEntryPointRecursively(e.children);else if(null!=e.endpoint){var n=e.id.substr(0,e.id.lastIndexOf("."));t.entryPoints.push(new b(n,e.endpoint))}}))}}]),t}(u["b"]);f=Object(d["a"])([u["a"]],f);var p=f,v=p,m=n("2877"),y=n("6544"),g=n.n(y),O=n("1f4f"),j=Object(m["a"])(v,i,s,!1,null,null,null);t["default"]=j.exports;g()(j,{VSimpleTable:O["a"]})}}]);