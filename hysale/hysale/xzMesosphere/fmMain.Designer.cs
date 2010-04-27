namespace xzMesosphere
{
    partial class fmMain
    {
        /// <summary>
        /// 必需的设计器变量。

        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。

        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。

        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(fmMain));
            this.muMain = new System.Windows.Forms.MenuStrip();
            this.muiSystem = new System.Windows.Forms.ToolStripMenuItem();
            this.muiServerConfig = new System.Windows.Forms.ToolStripMenuItem();
            this.muiSettingCOM = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem2 = new System.Windows.Forms.ToolStripSeparator();
            this.muiRegServer = new System.Windows.Forms.ToolStripMenuItem();
            this.muiTools = new System.Windows.Forms.ToolStripMenuItem();
            this.muiModule = new System.Windows.Forms.ToolStripMenuItem();
            this.muiReport = new System.Windows.Forms.ToolStripMenuItem();
            this.muiClearTables = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.muiSystemParam = new System.Windows.Forms.ToolStripMenuItem();
            this.muiModuleParam = new System.Windows.Forms.ToolStripMenuItem();
            this.muiPopedomEx = new System.Windows.Forms.ToolStripMenuItem();
            this.muiWindows = new System.Windows.Forms.ToolStripMenuItem();
            this.stsMain = new System.Windows.Forms.StatusStrip();
            this.BottomToolStripPanel = new System.Windows.Forms.ToolStripPanel();
            this.TopToolStripPanel = new System.Windows.Forms.ToolStripPanel();
            this.RightToolStripPanel = new System.Windows.Forms.ToolStripPanel();
            this.LeftToolStripPanel = new System.Windows.Forms.ToolStripPanel();
            this.ContentPanel = new System.Windows.Forms.ToolStripContentPanel();
            this.tlbDb = new System.Windows.Forms.ToolStripButton();
            this.tlbPort = new System.Windows.Forms.ToolStripButton();
            this.tlbRegServer = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tlbQuit = new System.Windows.Forms.ToolStripButton();
            this.nfMessage = new System.Windows.Forms.NotifyIcon(this.components);
            this.cmMessage = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.tmView = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tmQuit = new System.Windows.Forms.ToolStripMenuItem();
            this.tlsMain = new System.Windows.Forms.ToolStrip();
            this.btnPort = new System.Windows.Forms.ToolStripButton();
            this.btnServerStart = new System.Windows.Forms.ToolStripButton();
            this.btnQuit = new System.Windows.Forms.ToolStripButton();
            this.muiDataParam = new System.Windows.Forms.ToolStripMenuItem();
            this.muMain.SuspendLayout();
            this.cmMessage.SuspendLayout();
            this.tlsMain.SuspendLayout();
            this.SuspendLayout();
            // 
            // muMain
            // 
            this.muMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.muiSystem,
            this.muiTools,
            this.muiWindows});
            this.muMain.Location = new System.Drawing.Point(0, 0);
            this.muMain.MdiWindowListItem = this.muiWindows;
            this.muMain.Name = "muMain";
            this.muMain.Size = new System.Drawing.Size(639, 24);
            this.muMain.TabIndex = 1;
            this.muMain.Text = "menuStrip1";
            // 
            // muiSystem
            // 
            this.muiSystem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.muiServerConfig,
            this.muiSettingCOM,
            this.toolStripMenuItem2,
            this.muiRegServer});
            this.muiSystem.Name = "muiSystem";
            this.muiSystem.Size = new System.Drawing.Size(41, 20);
            this.muiSystem.Text = "系统";
            // 
            // muiServerConfig
            // 
            this.muiServerConfig.Name = "muiServerConfig";
            this.muiServerConfig.Size = new System.Drawing.Size(152, 22);
            this.muiServerConfig.Text = "服务端口配置";
            this.muiServerConfig.Click += new System.EventHandler(this.btnServerConfig_Click);
            // 
            // muiSettingCOM
            // 
            this.muiSettingCOM.Name = "muiSettingCOM";
            this.muiSettingCOM.Size = new System.Drawing.Size(152, 22);
            this.muiSettingCOM.Text = "COM端口配置";
            this.muiSettingCOM.Click += new System.EventHandler(this.muiSettingCOM_Click);
            // 
            // toolStripMenuItem2
            // 
            this.toolStripMenuItem2.Name = "toolStripMenuItem2";
            this.toolStripMenuItem2.Size = new System.Drawing.Size(149, 6);
            // 
            // muiRegServer
            // 
            this.muiRegServer.Name = "muiRegServer";
            this.muiRegServer.Size = new System.Drawing.Size(152, 22);
            this.muiRegServer.Text = "启动服务";
            this.muiRegServer.Click += new System.EventHandler(this.btnServerStart_Click);
            // 
            // muiTools
            // 
            this.muiTools.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.muiModule,
            this.muiDataParam,
            this.muiReport,
            this.muiClearTables,
            this.toolStripMenuItem1,
            this.muiSystemParam,
            this.muiModuleParam,
            this.muiPopedomEx});
            this.muiTools.Name = "muiTools";
            this.muiTools.Size = new System.Drawing.Size(41, 20);
            this.muiTools.Text = "工具";
            // 
            // muiModule
            // 
            this.muiModule.Name = "muiModule";
            this.muiModule.Size = new System.Drawing.Size(152, 22);
            this.muiModule.Text = "功能模块";
            this.muiModule.Click += new System.EventHandler(this.muiModule_Click);
            // 
            // muiReport
            // 
            this.muiReport.Name = "muiReport";
            this.muiReport.Size = new System.Drawing.Size(152, 22);
            this.muiReport.Text = "报表模块";
            this.muiReport.Click += new System.EventHandler(this.muiReport_Click);
            // 
            // muiClearTables
            // 
            this.muiClearTables.Name = "muiClearTables";
            this.muiClearTables.Size = new System.Drawing.Size(152, 22);
            this.muiClearTables.Text = "清除表记录";
            this.muiClearTables.Click += new System.EventHandler(this.muiClearTables_Click);
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(149, 6);
            // 
            // muiSystemParam
            // 
            this.muiSystemParam.Name = "muiSystemParam";
            this.muiSystemParam.Size = new System.Drawing.Size(152, 22);
            this.muiSystemParam.Text = "企业信息";
            this.muiSystemParam.Click += new System.EventHandler(this.muiSystemParam_Click);
            // 
            // muiModuleParam
            // 
            this.muiModuleParam.Name = "muiModuleParam";
            this.muiModuleParam.Size = new System.Drawing.Size(152, 22);
            this.muiModuleParam.Text = "模块参数";
            this.muiModuleParam.Click += new System.EventHandler(this.muiModuleParam_Click);
            // 
            // muiPopedomEx
            // 
            this.muiPopedomEx.Name = "muiPopedomEx";
            this.muiPopedomEx.Size = new System.Drawing.Size(152, 22);
            this.muiPopedomEx.Text = "增强权限";
            this.muiPopedomEx.Click += new System.EventHandler(this.muiPopedomEx_Click);
            // 
            // muiWindows
            // 
            this.muiWindows.MergeIndex = 1;
            this.muiWindows.Name = "muiWindows";
            this.muiWindows.Size = new System.Drawing.Size(41, 20);
            this.muiWindows.Text = "窗体";
            // 
            // stsMain
            // 
            this.stsMain.Location = new System.Drawing.Point(0, 326);
            this.stsMain.Name = "stsMain";
            this.stsMain.Size = new System.Drawing.Size(639, 22);
            this.stsMain.TabIndex = 3;
            this.stsMain.Text = "statusStrip1";
            // 
            // BottomToolStripPanel
            // 
            this.BottomToolStripPanel.Location = new System.Drawing.Point(0, 0);
            this.BottomToolStripPanel.Name = "BottomToolStripPanel";
            this.BottomToolStripPanel.Orientation = System.Windows.Forms.Orientation.Horizontal;
            this.BottomToolStripPanel.RowMargin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.BottomToolStripPanel.Size = new System.Drawing.Size(0, 0);
            // 
            // TopToolStripPanel
            // 
            this.TopToolStripPanel.Location = new System.Drawing.Point(0, 0);
            this.TopToolStripPanel.Name = "TopToolStripPanel";
            this.TopToolStripPanel.Orientation = System.Windows.Forms.Orientation.Horizontal;
            this.TopToolStripPanel.RowMargin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.TopToolStripPanel.Size = new System.Drawing.Size(0, 0);
            // 
            // RightToolStripPanel
            // 
            this.RightToolStripPanel.Location = new System.Drawing.Point(0, 0);
            this.RightToolStripPanel.Name = "RightToolStripPanel";
            this.RightToolStripPanel.Orientation = System.Windows.Forms.Orientation.Horizontal;
            this.RightToolStripPanel.RowMargin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.RightToolStripPanel.Size = new System.Drawing.Size(0, 0);
            // 
            // LeftToolStripPanel
            // 
            this.LeftToolStripPanel.Location = new System.Drawing.Point(0, 0);
            this.LeftToolStripPanel.Name = "LeftToolStripPanel";
            this.LeftToolStripPanel.Orientation = System.Windows.Forms.Orientation.Horizontal;
            this.LeftToolStripPanel.RowMargin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.LeftToolStripPanel.Size = new System.Drawing.Size(0, 0);
            // 
            // ContentPanel
            // 
            this.ContentPanel.BackColor = System.Drawing.SystemColors.ButtonShadow;
            this.ContentPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ContentPanel.Size = new System.Drawing.Size(617, 400);
            // 
            // tlbDb
            // 
            this.tlbDb.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tlbDb.Image = global::xzMesosphere.Properties.Resources.DbConnect;
            this.tlbDb.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tlbDb.Name = "tlbDb";
            this.tlbDb.Size = new System.Drawing.Size(23, 22);
            this.tlbDb.Text = "配置数据库";
            // 
            // tlbPort
            // 
            this.tlbPort.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tlbPort.Image = global::xzMesosphere.Properties.Resources.ServerPort;
            this.tlbPort.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tlbPort.Name = "tlbPort";
            this.tlbPort.Size = new System.Drawing.Size(23, 22);
            this.tlbPort.Text = "配置服务端口";
            this.tlbPort.Click += new System.EventHandler(this.btnServerConfig_Click);
            // 
            // tlbRegServer
            // 
            this.tlbRegServer.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tlbRegServer.Image = global::xzMesosphere.Properties.Resources.ServerStart;
            this.tlbRegServer.Name = "tlbRegServer";
            this.tlbRegServer.Size = new System.Drawing.Size(23, 22);
            this.tlbRegServer.Text = "启动服务";
            this.tlbRegServer.Click += new System.EventHandler(this.btnServerStart_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            // 
            // tlbQuit
            // 
            this.tlbQuit.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tlbQuit.Image = global::xzMesosphere.Properties.Resources.Quit;
            this.tlbQuit.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tlbQuit.Name = "tlbQuit";
            this.tlbQuit.Size = new System.Drawing.Size(23, 22);
            this.tlbQuit.Text = "退出系统";
            this.tlbQuit.Click += new System.EventHandler(this.tlbQuit_Click);
            // 
            // nfMessage
            // 
            this.nfMessage.BalloonTipIcon = System.Windows.Forms.ToolTipIcon.Info;
            this.nfMessage.ContextMenuStrip = this.cmMessage;
            this.nfMessage.Visible = true;
            this.nfMessage.DoubleClick += new System.EventHandler(this.nfMessage_DoubleClick);
            // 
            // cmMessage
            // 
            this.cmMessage.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tmView,
            this.toolStripSeparator2,
            this.tmQuit});
            this.cmMessage.Name = "cmMessage";
            this.cmMessage.Size = new System.Drawing.Size(95, 54);
            // 
            // tmView
            // 
            this.tmView.Name = "tmView";
            this.tmView.Size = new System.Drawing.Size(94, 22);
            this.tmView.Text = "查看";
            this.tmView.Click += new System.EventHandler(this.tmView_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(91, 6);
            // 
            // tmQuit
            // 
            this.tmQuit.Name = "tmQuit";
            this.tmQuit.Size = new System.Drawing.Size(94, 22);
            this.tmQuit.Text = "退出";
            this.tmQuit.Click += new System.EventHandler(this.tmQuit_Click);
            // 
            // tlsMain
            // 
            this.tlsMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.btnPort,
            this.btnServerStart,
            this.btnQuit});
            this.tlsMain.Location = new System.Drawing.Point(0, 24);
            this.tlsMain.Name = "tlsMain";
            this.tlsMain.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
            this.tlsMain.Size = new System.Drawing.Size(639, 25);
            this.tlsMain.TabIndex = 6;
            // 
            // btnPort
            // 
            this.btnPort.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.btnPort.Image = global::xzMesosphere.Properties.Resources.ServerPort;
            this.btnPort.Name = "btnPort";
            this.btnPort.Size = new System.Drawing.Size(23, 22);
            this.btnPort.Text = "端口配置";
            this.btnPort.ToolTipText = "通信端口配置";
            this.btnPort.Click += new System.EventHandler(this.btnServerConfig_Click);
            // 
            // btnServerStart
            // 
            this.btnServerStart.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.btnServerStart.Image = global::xzMesosphere.Properties.Resources.ServerStart;
            this.btnServerStart.Name = "btnServerStart";
            this.btnServerStart.Size = new System.Drawing.Size(23, 22);
            this.btnServerStart.Text = "注册";
            this.btnServerStart.ToolTipText = "注册应用服务";
            this.btnServerStart.Click += new System.EventHandler(this.btnServerStart_Click);
            // 
            // btnQuit
            // 
            this.btnQuit.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.btnQuit.Image = global::xzMesosphere.Properties.Resources.Quit;
            this.btnQuit.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.btnQuit.Name = "btnQuit";
            this.btnQuit.Size = new System.Drawing.Size(23, 22);
            this.btnQuit.Text = "退出";
            this.btnQuit.Click += new System.EventHandler(this.tlbQuit_Click);
            // 
            // muiDataParam
            // 
            this.muiDataParam.Name = "muiDataParam";
            this.muiDataParam.Size = new System.Drawing.Size(152, 22);
            this.muiDataParam.Text = "数据参数";
            this.muiDataParam.Click += new System.EventHandler(this.muiDataParam_Click);
            // 
            // fmMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(639, 348);
            this.Controls.Add(this.stsMain);
            this.Controls.Add(this.tlsMain);
            this.Controls.Add(this.muMain);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.IsMdiContainer = true;
            this.Name = "fmMain";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "应用服务层";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.muMain.ResumeLayout(false);
            this.muMain.PerformLayout();
            this.cmMessage.ResumeLayout(false);
            this.tlsMain.ResumeLayout(false);
            this.tlsMain.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip muMain;
        private System.Windows.Forms.ToolStripMenuItem muiSystem;
        private System.Windows.Forms.ToolStripMenuItem muiServerConfig;
        private System.Windows.Forms.ToolStripMenuItem muiRegServer;
        private System.Windows.Forms.StatusStrip stsMain;
        private System.Windows.Forms.ToolStripMenuItem muiWindows;
        private System.Windows.Forms.ToolStripPanel BottomToolStripPanel;
        private System.Windows.Forms.ToolStripPanel TopToolStripPanel;
        private System.Windows.Forms.ToolStripPanel RightToolStripPanel;
        private System.Windows.Forms.ToolStripPanel LeftToolStripPanel;
        private System.Windows.Forms.ToolStripContentPanel ContentPanel;
        private System.Windows.Forms.ToolStripButton tlbDb;
        private System.Windows.Forms.ToolStripButton tlbPort;
        private System.Windows.Forms.ToolStripButton tlbRegServer;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripButton tlbQuit;
        private System.Windows.Forms.ToolStripMenuItem muiTools;
        private System.Windows.Forms.ToolStripMenuItem muiModule;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem2;
        private System.Windows.Forms.ToolStripMenuItem muiModuleParam;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem muiSystemParam;
        private System.Windows.Forms.ToolStripMenuItem muiReport;
        private System.Windows.Forms.ToolStripMenuItem muiClearTables;
        private System.Windows.Forms.ToolStripMenuItem muiSettingCOM;
        private System.Windows.Forms.ToolStripMenuItem muiPopedomEx;
        private System.Windows.Forms.NotifyIcon nfMessage;
        private System.Windows.Forms.ContextMenuStrip cmMessage;
        private System.Windows.Forms.ToolStripMenuItem tmView;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tmQuit;
        private System.Windows.Forms.ToolStrip tlsMain;
        private System.Windows.Forms.ToolStripButton btnPort;
        private System.Windows.Forms.ToolStripButton btnServerStart;
        private System.Windows.Forms.ToolStripButton btnQuit;
        private System.Windows.Forms.ToolStripMenuItem muiDataParam;
    }
}

