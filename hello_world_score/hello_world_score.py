from iconservice import *

TAG = 'HelloWorldScore'


class HelloWorldScore(IconScoreBase):

    def __init__(self, db: IconScoreDatabase) -> None:
        super().__init__(db)

        self._count = DictDB('count', db, value_type=int)

    def on_install(self) -> None:
        super().on_install()

    def on_update(self) -> None:
        super().on_update()

    @external(readonly=True)
    def hello(self) -> str:
        Logger.debug(f'Hello, world!', TAG)
        return "Hello"

    @external(readonly=True)
    def greet(self) -> str:
        return f'Hello, {self.msg.sender}'

    @external
    @payable
    def increment(self):
        sender = self.msg.sender
        self._count[sender] = self._count[sender] + 1

    @external(readonly=True)
    def my_value(self) -> str:
        sender = self.msg.sender
        return str(self._count[sender])
