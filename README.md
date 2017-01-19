# gbdt-score

xgboost训练出来的模型是二进制文件，本项目把二进制文件反解析，然后用来打分。xgboost工程里面打分部分加了synchronized锁，所以打分效率较低，本工程使用java实现，打分部分线程安全。
