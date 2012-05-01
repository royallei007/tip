<?php get_header(); ?>
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
	<div class="image">
		<div class="nav prev"><?php next_post_link('%link','&lsaquo;') ?></div>
		<?php the_image(); ?>
		<div class="nav next"><?php previous_post_link('%link','&rsaquo;'); ?></div>
	</div>
	<?php partial('post'); ?>	
	<?php comments_template(); ?>
<?php endwhile; else : ?>
	<h2 class="center">Not Found</h2>
	<p class="center">Sorry, but you are looking for something that isn't here.</p>
	<?php include (TEMPLATEPATH . "/searchform.php"); ?>
<?php endif; ?>
<?php get_footer(); ?>
