.PHONY: all clean run

SOURCES = \
	ControlLayer/CustomerCtrl \
	ControlLayer/LeaseCtrl \
	ControlLayer/OrderCtrl \
	ControlLayer/PersonCtrl \
	ControlLayer/ProductCtrl \
	ControlLayer/ProductLocationCtrl \
	ControlLayer/PurchaseCtrl \
	ControlLayer/SalesAssistantCtrl \
	ControlLayer/StatisticCtrl \
	ModelLayer/Business \
	ModelLayer/Customer \
	ModelLayer/CustomerContainer \
	ModelLayer/Discount \
	ModelLayer/Lease \
	ModelLayer/LeaseContainer \
	ModelLayer/LeasingItem \
	ModelLayer/LeasingItemContainer \
	ModelLayer/Order \
	ModelLayer/OrderContainer \
	ModelLayer/OrderLine \
	ModelLayer/OrderStatus \
	ModelLayer/OrderStatusContainer \
	ModelLayer/Person \
	ModelLayer/PersonContainer \
	ModelLayer/Product \
	ModelLayer/ProductCategory \
	ModelLayer/ProductCategoryContainer \
	ModelLayer/ProductContainer \
	ModelLayer/ProductGroup \
	ModelLayer/ProductGroupContainer \
	ModelLayer/ProductGroupItem \
	ModelLayer/ProductLocation \
	ModelLayer/ProductLocationContainer \
	ModelLayer/ProductPhysicalAvail \
	ModelLayer/Purchase \
	ModelLayer/PurchaseContainer \
	ModelLayer/SalesAssistant \
	ModelLayer/SalesAssistantContainer \
	ModelLayer/SerialNumber \
	TUILayer/CustomerMenuUI \
	TUILayer/StatisticMenuUI \
	TUILayer/GlobalUI \
	TUILayer/LoginUI \
	TUILayer/MainMenuUI \
	TUILayer/ProductLocationMenuUI \
	TUILayer/ProductMenuUI \
	TUILayer/RentalMenuUI \
	TUILayer/SalesAssistantMenuUI \
	TUILayer/SalesMenuUI \
	GUILayer/GlobalUI \
	GUILayer/LoginUI \
	GUILayer/SystemUI \
	GUILayer/Product/CreateUI \
	GUILayer/Product/Group/CreateUI \
	GUILayer/Product/Category/CreateUI \
	GUILayer/AboutUI \
	TestData \
	Program
	

SRC = $(addprefix src/, $(addsuffix .java, $(SOURCES)))
BIN = $(addprefix bin/, $(addsuffix .class, $(SOURCES)))

all: ${BIN}

clean:
	-rm -f ${BIN};

${BIN}: ${SRC}
	mkdir -p bin/ControlLayer;
	mkdir -p bin/ModelLayer;
	mkdir -p bin/TUILayer;
	javac -d bin ${SRC};

run: all
	java -cp bin Program;
