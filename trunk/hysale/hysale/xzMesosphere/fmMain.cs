using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Reflection;
using System.Runtime.Remoting;
using System.Data.SqlClient;
using System.IO;
using xzMesosphere.Source;
using XzzLibrary.Source;
using XzzLibrary.Source.Dialog;

namespace xzMesosphere
{
    public partial class fmMain : Form
    {
        public xzServerManage FServerManage = new xzServerManage();

        public fmMain()
        {
            InitializeComponent();
            Initial();
        }

        private void Initial()
        {
            string filename = xzConstDir.GetCurrentPath() + "\\Server.exe.config";
            if (File.Exists(filename))  RemotingConfiguration.Configure(filename, true);
            else MessageBoxEx.WarnMessage(string.Format("服务配置文件[{0}]不存在", filename));

            FServerManage.FileName = "Mesosphere.xsr";
            filename = xzConstDir.GetCurrentPath() + "\\" + FServerManage.FileName;
            if (File.Exists(filename))
            {
                FServerManage.LoadServerManage(filename);
                FServerManage.TCPPortState = true;
                FServerManage.HTTPPortState = true;
            }
            else MessageBoxEx.WarnMessage(string.Format("服务配置文件[{0}]不存在", filename));
             
            nfMessage.Icon = global::xzMesosphere.Properties.Resources.Project;
            nfMessage.Visible = true;
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);
            Visible = false;
            e.Cancel = true;
        }

        private void RegServer()
        {
            //公用配件
            LoginServer.Login(FServerManage);
            hyLogin.Login(FServerManage);
            //华远
            HyLoginStorage.Login(FServerManage);      //仓库
            HyLoginFinance.Login(FServerManage);      //应收应付款
            HyLoginAccount.Login(FServerManage);      //应收应付款
            HYLoginArchives.Login(FServerManage);
            //海灵
            Ft001Login.Login(FServerManage);
            MessageBox.Show("远程服务启动完成");
        }

        private void muiModule_Click(object sender, EventArgs e)
        {
            fmxzRestrict fmBase = new fmxzRestrict();
            ModuleUtensil.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            ModuleUtensil FUtensil = new ModuleUtensil();
            fmBase.Utensil = FUtensil;
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void muiReport_Click(object sender, EventArgs e)
        {
            fmxzRestrict fmBase = new fmxzRestrict();
            ReportUtensil.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            ReportUtensil FUtensil = new ReportUtensil();
            fmBase.Utensil = FUtensil;
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void muiPopedomEx_Click(object sender, EventArgs e)
        {
            fmxzRestrict fmBase = new fmxzRestrict();
            PopedomExUtensil.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            PopedomExUtensil FUtensil = new PopedomExUtensil();
            fmBase.Utensil = FUtensil;
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void muiModuleParam_Click(object sender, EventArgs e)
        {
            fmxzDataSet.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            fmxzDataSet fmBase = new fmxzDataSet();
            fmBase.Utensil = null;
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void muiSystemParam_Click(object sender, EventArgs e)
        {
            fmxzSystem.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            fmxzSystem fmBase = new fmxzSystem();
            fmBase.MdiParent = this;
            fmBase.Show();
            fmBase.bdgMain.DataChange();
        }

        private void muiClearTables_Click(object sender, EventArgs e)
        {
            fmxzClearTable.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            fmxzClearTable fmBase = new fmxzClearTable();
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void muiSettingCOM_Click(object sender, EventArgs e)
        {
            fmxzSettingCOM fmBase = new fmxzSettingCOM();
            //fmBase.SendCollection = FSendCollection;
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void btnServerConfig_Click(object sender, EventArgs e)
        {
            fmxzServer fmBase = new fmxzServer();
            fmBase.ServerManage = FServerManage;
            fmBase.Initial();
            fmBase.MdiParent = this;
            fmBase.Show();
        }

        private void btnServerStart_Click(object sender, EventArgs e)
        {           
            RegServer();
        }

        private void tlbQuit_Click(object sender, EventArgs e)
        {
            Dispose();
            Application.Exit();
        }

        private void nfMessage_DoubleClick(object sender, EventArgs e)
        {
            if (!Visible) Show();
            else WindowState = FormWindowState.Maximized;
        }

        private void tmView_Click(object sender, EventArgs e)
        {
            if (!Visible) Show();
            else WindowState = FormWindowState.Maximized;
        }

        private void tmQuit_Click(object sender, EventArgs e)
        {
            Dispose();
            Application.Exit();
        }

        private void muiDataParam_Click(object sender, EventArgs e)
        {
            fmxzTreeManage fmBase = new fmxzTreeManage();
            ExtendDataUtensil.ConnectStr = xzSqlConnect.ConnectionString(FServerManage.ServerManageParam.SqlConnectParam);
            ExtendDataUtensil FUtensil = new ExtendDataUtensil();
            fmBase.Utensil = FUtensil;
            fmBase.MdiParent = this;
            fmBase.Show();
        }
    }
}