# javaOcp-20250218： 家具店管理系統
### 使用技術
- Java
  - Jframe: UI畫面
  - Apachi POI: excel輸出
  - JfreeChart: 圖表產出
- MySQL
#### 設計模式
- MVC
- dao pattern
# 使用方式
### 1. 下載所需檔案
本頁面右上方Code按鈕，選擇 Download ZIP 下載所有檔案
若無需原始碼可手動下載 HomeWork4.jar、db.txt、與db資料夾內檔案
### 2. 準備環境
- 確認電腦環境已安裝Java 11與MySQL
- 開啟MySQL Workbench並連接到MySQL伺服器
- 匯入`db`資料夾內的所有檔案，並確認是否出現`furnitureshop`的Schema
### 3. 執行程式
- (Windows) 雙擊ConsoleShop.jar即可執行
- 或開啟終端，移動到ConsoleShop.jar所在資料夾，並輸入‵java -jar FurnitureShop.jar`
# 目錄結構
```
src/
├── controller/
│   ├── customer/
│   │   ├── CheckoutUI 確認訂單與結帳
│   │   ├── CustomerMenuUI 顧客主選單
│   │   ├── CustomerUpdateUI 更新顧客資訊
│   │   ├── ShopFloor4UI 購買商品(兒童用品類別)
│   │   ├── ShopFloor3UI 購買商品(居家裝飾類別)
│   │   ├── ShopFloor2UI 購買商品(家具類別)
│   │   ├── ShopFloor1UI 購買商品(美食類別)
│   │   └── ViewOrderUI 查詢訂單紀錄
│   ├── employee/
│   │   ├── AddPorderUI 員工新增訂單
│   │   ├── EmployeeMenuUI 員工主選單
│   │   ├── ManageCustomerUI 顧客管理
│   │   ├── ManageEmployeeUI 員工管理
│   │   ├── ManageOrderUI 訂單管理
│   │   ├── ManageProductUI 產品管理 
│   │   ├── NewEmployeeUI 新增員工
│   │   ├── ViewChartUI 查看報表
│   │   └── UpdatePorderUI 員工修改訂單
│   └── login/
│       ├── ChooseRoleUI 選擇登入身分
│       ├── CustomerLoginUI 顧客登入
│       ├── CustomerRegisterUI 顧客註冊
│       └── EmployeeLoginUI 員工註冊
├── dao/
│   ├── impl/
│   │   ├── CustomerDaoImpl 實作顧客資料表獲取
│   │   ├── EmployeeDaoImpl 實作員工資料表獲取
│   │   ├── OrderDetailDaoImpl 實作訂單詳細資訊獲取
│   │   ├── PorderDaoImpl 實作訂單資料表獲取
│   │   └── ProductDaoImpl 實作產品資料表獲取
│   ├── CustomerDao 顧客資料表獲取
│   ├── EmployeeDao 員工資料表獲取
│   ├── OrderDetailDao 訂單詳細資訊獲取
│   ├── PorderDao 訂單資料表獲取
│   └── ProductDao 產品資料表獲取
├── model/
│   ├── CartItem 購物車商品類別
│   ├── Customer 顧客類別
│   ├── Employee 員工類別
│   ├── OrderItem 訂單內容類別
│   ├── Porder 訂單類別
│   └── Product 商品類別
├── service/
│   ├── impl/
│   │   ├── CustomerServiceImpl 實作顧客資料表與UI的連結
│   │   ├── EmployeeServiceImpl 實作員工資料表與UI的連結
│   │   ├── PorderServiceImpl 實作訂單/訂單詳細資料表與UI的連結
│   │   └── ProductServiceImpl 實作產品資料表與UI的連結
│   ├── CustomerService 顧客資料表與UI的連結
│   ├── EmployeeService 員工資料表與UI的連結
│   ├── PorderService 訂單/訂單詳細資料表與UI的連結
│   └── ProductService 產品資料表與UI的連結
└── util/
    ├── ClockPanel  時鐘UI元件
    ├── DBConnection 連結資料庫
    ├── ExcelTool 寫入Excel
    ├── FileTool 檔案存取
    ├── Helper 工具(如驗證輸入格式)
    └── TitlePanel 標題UI元件
```
# 流程圖
![FlowChart](image/flowchart.png)