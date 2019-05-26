import pygame, sys, pygame.mixer, time, os
from pygame.locals import *

EMPTY = 0
BLACK = 1
WHITE = 2
INFINITY = 999999999
MAX = 0
MIN = 1
DEFAULT_LEVEL = 2
HUMAN = "human"
COMPUTER = "computer"
RANDOM = "random"


class Gui:
    def __init__(self):

        pygame.init()
        pygame.display.set_caption('Othello Game')

        bg = pygame.image.load('res/back.png')
        bg = pygame.transform.scale(bg, (640, 480))

        self.BLACK = (0, 0, 0)
        self.BACKGROUND = (236, 134, 34)
        self.WHITE = (255, 255, 255)
        self.BLUE = (0, 0, 255)
        self.GRAY = (160, 160, 160)

        self.SCREEN_SIZE = (640, 480)
        self.BOARD_POS = (100, 20)
        self.BOARD = (120, 40)
        self.BOARD_SIZE = 400
        self.SQUARE_SIZE = 50
        self.screen = pygame.display.set_mode(self.SCREEN_SIZE)

        self.BLACK_LAB_POS = (5, self.SCREEN_SIZE[1] / 4)
        self.WHITE_LAB_POS = (560, self.SCREEN_SIZE[1] / 4)
        self.scoreFont = pygame.font.SysFont("Impact", 50)
        self.board_img = pygame.image.load(os.path.join(
            "res", "board.bmp")).convert()
        self.black_img = pygame.image.load(os.path.join(
            "res", "preta.bmp")).convert()
        self.white_img = pygame.image.load(os.path.join(
            "res", "branca.bmp")).convert()
        self.tip_img = pygame.image.load(os.path.join("res",
                                                      "tip.bmp")).convert()
        self.clear_img = pygame.image.load(os.path.join("res",
                                                        "nada.bmp")).convert()
    def show_options(self):
        player1 = HUMAN
        player2 = COMPUTER
        level = DEFAULT_LEVEL

        while True:
            bg = pygame.image.load('res/back.png')
            bg = pygame.transform.scale(bg, (640, 480))
            title_image = pygame.image.load('res/Othello.png')
            title_pos = title_image.get_rect(centerx=self.screen.get_width() / 2, centery=120)

            start_img = pygame.image.load('res/start.png')
            start_pos = start_img.get_rect(
                centerx=self.screen.get_width() / 2, centery=280)
            player1_txt = pygame.image.load('res/first-player.png')
            player1_pos = player1_txt.get_rect(
                centerx=self.screen.get_width() / 2, centery=320)
            player2_txt = pygame.image.load('res/second-player.png')
            player2_pos = player2_txt.get_rect(
                centerx=self.screen.get_width() / 2, centery=360)

            self.screen.blit(bg, [0,0])
            self.screen.blit(title_image, title_pos)
            self.screen.blit(start_img, start_pos)
            self.screen.blit(player1_txt, player1_pos)
            self.screen.blit(player2_txt, player2_pos)

            for event in pygame.event.get():
                if event.type == QUIT:
                    sys.exit(0)
                elif event.type == MOUSEBUTTONDOWN:
                    (mouse_x, mouse_y) = pygame.mouse.get_pos()
                    if start_pos.collidepoint(mouse_x, mouse_y):
                        return (player1, player2, level)
                    elif player1_pos.collidepoint(mouse_x, mouse_y):
                        player1 = self.get_chosen_player()
                    elif player2_pos.collidepoint(mouse_x, mouse_y):
                        player2 = self.get_chosen_player()

            pygame.display.flip()

    def show_winner(self, player_color):
        bg = pygame.image.load('res/back.png')
        bg = pygame.transform.scale(bg, (640, 480))

        win_white = pygame.image.load('res/win-white.png')
        white_pos = win_white.get_rect(centerx=self.screen.get_width() / 2, centery=280)

        win_black = pygame.image.load('res/win-black.png')
        black_pos = win_black.get_rect(centerx=self.screen.get_width() / 2, centery=280)

        tie = pygame.image.load('res/its-a-tie.png')
        tie_pos = tie.get_rect(centerx=self.screen.get_width() / 2, centery=280)

        if player_color == WHITE:
            msg = pygame.image.load('res/win-white.png')
        elif player_color == BLACK:
            msg = pygame.image.load('res/win-black.png')
        else:
            msg = pygame.image.load('res/its-a-tie.png')
        self.screen.blit(bg, [0,0])
        self.screen.blit(
            msg, msg.get_rect(
                centerx=self.screen.get_width() / 2, centery=160))
        pygame.display.flip()

    def get_chosen_player(self):
        while True:
            bg = pygame.image.load('res/back.png')
            bg = pygame.transform.scale(bg, (640, 480))

            player_img = pygame.image.load('res/player.png')
            player_pos = player_img.get_rect(
                centerx=self.screen.get_width() / 2, centery=80)

            human_img = pygame.image.load('res/Human.png')
            human_pos = human_img.get_rect(
                centerx=self.screen.get_width() / 2, centery=180)
            random_txt = pygame.image.load('res/Random.png')
            random_pos = random_txt.get_rect(
                centerx=self.screen.get_width() / 2, centery=300)
            comp_txt = pygame.image.load('res/Computer.png')
            comp_pos = comp_txt.get_rect(
                centerx=self.screen.get_width() / 2, centery=420)

            self.screen.blit(bg, [0,0])
            self.screen.blit(player_img, player_pos)
            self.screen.blit(human_img, human_pos)
            self.screen.blit(comp_txt, comp_pos)
            self.screen.blit(random_txt, random_pos)

            for event in pygame.event.get():
                if event.type == QUIT:
                    sys.exit(0)
                elif event.type == MOUSEBUTTONDOWN:
                    (mouse_x, mouse_y) = pygame.mouse.get_pos()
                    if human_pos.collidepoint(mouse_x, mouse_y):
                        return HUMAN
                    elif comp_pos.collidepoint(mouse_x, mouse_y):
                        return COMPUTER
                    elif random_pos.collidepoint(mouse_x, mouse_y):
                        return RANDOM

            pygame.display.flip()

    def show_game(self):
        bg = pygame.image.load('res/back.png')
        bg = pygame.transform.scale(bg, (640, 480))

        self.background = pygame.Surface(self.screen.get_size()).convert()
        self.background.fill(self.BACKGROUND)
        self.score_size = 50
        self.score1 = pygame.Surface((self.score_size, self.score_size))
        self.score2 = pygame.Surface((self.score_size, self.score_size))
        self.screen.blit(self.background, [0, 0], self.background.get_rect())
        self.screen.blit(bg, [0, 0])
        self.screen.blit(self.board_img, self.BOARD_POS,
                         self.board_img.get_rect())
        self.put_stone((3, 3), WHITE)
        self.put_stone((4, 4), WHITE)
        self.put_stone((3, 4), BLACK)
        self.put_stone((4, 3), BLACK)
        pygame.display.flip()

    def put_stone(self, pos, color):
        if pos == None:
            return

        pos = (pos[1], pos[0])

        if color == BLACK:
            img = self.black_img
        elif color == WHITE:
            img = self.white_img
        else:
            img = self.tip_img

        x = pos[0] * self.SQUARE_SIZE + self.BOARD[0]
        y = pos[1] * self.SQUARE_SIZE + self.BOARD[1]

        self.screen.blit(img, (x, y), img.get_rect())
        pygame.display.flip()

    def clear_square(self, pos):

        pos = (pos[1], pos[0])

        x = pos[0] * self.SQUARE_SIZE + self.BOARD[0]
        y = pos[1] * self.SQUARE_SIZE + self.BOARD[1]
        self.screen.blit(self.clear_img, (x, y), self.clear_img.get_rect())
        pygame.display.flip()

    def get_mouse_input(self):
        while True:
            for event in pygame.event.get():
                if event.type == MOUSEBUTTONDOWN:
                    (mouse_x, mouse_y) = pygame.mouse.get_pos()

                    if mouse_x > self.BOARD_SIZE + self.BOARD[0] or \
                       mouse_x < self.BOARD[0] or \
                       mouse_y > self.BOARD_SIZE + self.BOARD[1] or \
                       mouse_y < self.BOARD[1]:
                        continue

                    position = ((mouse_x - self.BOARD[0]) // self.SQUARE_SIZE), \
                               ((mouse_y - self.BOARD[1]) // self.SQUARE_SIZE)
                    position = (position[1], position[0])
                    return position

                elif event.type == QUIT:
                    sys.exit(0)

            time.sleep(.05)

    def update(self, board, blacks, whites, current_player_color):

        for i in range(8):
            for j in range(8):
                if board[i][j] != 0:
                    self.put_stone((i, j), board[i][j])

        blacks_str = ' '+ '%02d ' % int(blacks)
        whites_str = '%02d ' % int(whites)
        self.showScore(blacks_str, whites_str, current_player_color)
        pygame.display.flip()

    def showScore(self, blackStr, whiteStr, current_player_color):
        black_background = self.WHITE if current_player_color == WHITE else self.WHITE
        white_background = self.WHITE if current_player_color == BLACK else self.WHITE
        text = self.scoreFont.render(blackStr, True, self.BLACK,
                                     black_background, )
        text2 = self.scoreFont.render(whiteStr, True, self.BLACK,
                                      white_background)
        self.screen.blit(text,
                         (self.BLACK_LAB_POS[0], self.BLACK_LAB_POS[1] + 40))
        self.screen.blit(text2,
                         (self.WHITE_LAB_POS[0], self.WHITE_LAB_POS[1] + 50))

    def wait_quit(self):

        for event in pygame.event.get():
            if event.type == QUIT:
                sys.exit(0)
            elif event.type == KEYDOWN:
                break