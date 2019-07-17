/**
 * Created by dongwen on 2017/8/7.
 */

var fsp = {
    desensitization : {
        nameDesensy:function(oldName) {
            var resultName = '';
            var lenth = oldName.length;
            var xing = '';
            var r = '';
            var rl = '';
            rl=rl.substring(lenth-6);
            if(lenth>0 ||lenth<2) {
                xing = '*';
                r = oldName.substring(0,1);
            }else if(lenth > 2) {
                xing = '**';
                rl = oldName.substring(1);
                rl=rl.substring(lenth-2);
            }
            resultName = r + xing + rl;
            return resultName;
        },
        mobileDesensy:function(oldMobile)        {
            var resultMobile = '';
            var lenth = oldMobile.length;
            var xing = '';
            for(var i=0;i<lenth-6;i++){
                xing += '*';
            }
            var r3 = oldMobile.substring(0,3);
            var rl3 = oldMobile.substring(3);
            rl3=rl3.substring(lenth-6);
            resultMobile = r3 + xing + rl3;
            return resultMobile;
        },
        idCardDesensy:function(oldId)        {
            var resultId = '';
            var lenth = oldId.length;
            var xing = '';
            for(var i=0;i<lenth-8;i++){
                xing += '*';
            }
            var r4 = oldId.substring(0,4);
            var rl4 = oldId.substring(4);
            rl4=rl4.substring(lenth-8);
            resultId = r4 + xing + rl4;
            return resultId;
        },
        bankCardDesensy:function(oldBank)        {
            var resultBank = '';
            var lenth = oldBank.length;
            var xing = '';
            for(var i=0;i<lenth-8;i++){
                xing += '*';
            }
            var r4 = oldBank.substring(0,4);
            var rl4 = oldBank.substring(4);
            rl4=rl4.substring(lenth-8);
            resultBank = r4 + xing + rl4;
            return resultBank;
        }
    }

}